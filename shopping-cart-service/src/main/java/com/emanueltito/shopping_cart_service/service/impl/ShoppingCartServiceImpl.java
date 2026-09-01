package com.emanueltito.shopping_cart_service.service.impl;

import com.emanueltito.shopping_cart_service.client.IProductFeignClient;
import com.emanueltito.shopping_cart_service.client.dto.ProductClientDTO;
import com.emanueltito.shopping_cart_service.dto.CartItemRequestDTO;
import com.emanueltito.shopping_cart_service.dto.ShoppingCartRequestDTO;
import com.emanueltito.shopping_cart_service.dto.ShoppingCartResponseDTO;
import com.emanueltito.shopping_cart_service.exception.ResourceNotFoundException;
import com.emanueltito.shopping_cart_service.mapper.CartItemMapper;
import com.emanueltito.shopping_cart_service.mapper.ShoppingCartMapper;
import com.emanueltito.shopping_cart_service.model.CartItem;
import com.emanueltito.shopping_cart_service.model.ShoppingCart;
import com.emanueltito.shopping_cart_service.repository.ShoppingCartRepository;
import com.emanueltito.shopping_cart_service.service.IShoppingCartService;
import com.emanueltito.shopping_cart_service.exception.ServiceUnavailableException;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingCartServiceImpl implements IShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final IProductFeignClient productFeignClient;

    @Override
    @Transactional
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallbackMethodSave")
    @Retry(name = "products-service")
    public ShoppingCartResponseDTO save(ShoppingCartRequestDTO requestDTO) {
        List<String> codes = requestDTO.getItems().stream()
                .map(CartItemRequestDTO::getProductCode)
                .distinct()
                .toList();

        List<ProductClientDTO> products = productFeignClient.getProductsByCodes(codes);
        Map<String, ProductClientDTO> productMap = products.stream()
                .collect(Collectors.toMap(ProductClientDTO::getCode, Function.identity()));

        List<CartItem> cartItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (CartItemRequestDTO itemDTO : requestDTO.getItems()) {
            ProductClientDTO product = productMap.get(itemDTO.getProductCode());
            if (product == null) {
                throw new ResourceNotFoundException("No se encontró el producto con código: " + itemDTO.getProductCode());
            }

            CartItem cartItem = CartItemMapper.toEntity(product, itemDTO.getQuantity());
            cartItems.add(cartItem);
            totalPrice = totalPrice.add(cartItem.getSubtotal());
        }

        ShoppingCart shoppingCart = ShoppingCart.builder()
                .totalPrice(totalPrice)
                .items(cartItems)
                .build();

        ShoppingCart savedCart = shoppingCartRepository.save(shoppingCart);
        log.info("Carrito de compras creado exitosamente con ID: {}", savedCart.getId());
        return ShoppingCartMapper.toResponseDTO(savedCart);
    }

    private ShoppingCartResponseDTO fallbackMethodSave(ShoppingCartRequestDTO requestDTO, Throwable throwable) {
        if (throwable instanceof ResourceNotFoundException) {
            throw (ResourceNotFoundException) throwable;
        }
        if (throwable instanceof FeignException.NotFound) {
            throw (FeignException.NotFound) throwable;
        }
        log.warn("Se ejecutó fallbackMethodSave debido a una falla en el microservicio products-service: {}", throwable.getMessage());
        throw new ServiceUnavailableException("El servicio de productos no está disponible temporalmente. Por favor, intente más tarde.");
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShoppingCartResponseDTO> findAll() {
        return shoppingCartRepository.findAll()
                .stream()
                .map(ShoppingCartMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ShoppingCartResponseDTO findById(Long id) {
        ShoppingCart cart = shoppingCartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carrito de compras no encontrado con id: " + id));
        return ShoppingCartMapper.toResponseDTO(cart);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!shoppingCartRepository.existsById(id)) {
            throw new ResourceNotFoundException("Carrito de compras no encontrado con id: " + id);
        }
        shoppingCartRepository.deleteById(id);
        log.info("Carrito de compras eliminado exitosamente con ID: {}", id);
    }
}
