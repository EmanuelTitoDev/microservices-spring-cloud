package com.emanueltito.sales_service.service.impl;

import com.emanueltito.sales_service.client.IShoppingCartFeignClient;
import com.emanueltito.sales_service.client.dto.CartClientDTO;
import com.emanueltito.sales_service.dto.SaleRequestDTO;
import com.emanueltito.sales_service.dto.SaleResponseDTO;
import com.emanueltito.sales_service.exception.ResourceNotFoundException;
import com.emanueltito.sales_service.mapper.SaleMapper;
import com.emanueltito.sales_service.model.Sale;
import com.emanueltito.sales_service.repository.SaleRepository;
import com.emanueltito.sales_service.service.ISaleService;
import com.emanueltito.sales_service.exception.ServiceUnavailableException;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaleServiceImpl implements ISaleService {

    private final SaleRepository saleRepository;
    private final IShoppingCartFeignClient shoppingCartFeignClient;

    @Override
    @Transactional
    @CircuitBreaker(name = "shopping-cart-service", fallbackMethod = "fallbackMethodSave")
    @Retry(name = "shopping-cart-service")
    public SaleResponseDTO save(SaleRequestDTO requestDTO) {
        // Valida que el carrito exista en shopping-cart-service mediante Feign
        CartClientDTO cart = shoppingCartFeignClient.getCartById(requestDTO.getIdCart());

        Sale sale = SaleMapper.toEntity(requestDTO);
        Sale savedSale = saleRepository.save(sale);

        log.info("Venta creada exitosamente con ID: {}", savedSale.getId());
        return SaleMapper.toResponseDTO(savedSale, cart);
    }

    private SaleResponseDTO fallbackMethodSave(SaleRequestDTO requestDTO, Throwable throwable) {
        if (throwable instanceof ResourceNotFoundException) {
            throw (ResourceNotFoundException) throwable;
        }
        if (throwable instanceof FeignException.NotFound) {
            throw (FeignException.NotFound) throwable;
        }
        log.warn("Se ejecutó fallbackMethodSave debido a una falla en el microservicio shopping-cart-service: {}", throwable.getMessage());
        throw new ServiceUnavailableException("El servicio de carritos de compra no está disponible temporalmente. Por favor, intente más tarde.");
    }

    @Override
    @Transactional(readOnly = true)
    public List<SaleResponseDTO> findAll() {
        return saleRepository.findAll()
                .stream()
                .map(sale -> {
                    CartClientDTO cart = null;
                    try {
                        cart = shoppingCartFeignClient.getCartById(sale.getIdCart());
                    } catch (Exception ignored) {
                        log.warn("El microservicio shopping-cart-service está temporalmente fuera de línea. Retornando venta {} sin información del carrito.", sale.getId());
                        // En caso de que el microservicio de carrito esté temporalmente fuera de línea,
                        // permitimos devolver la venta con el carrito en null.
                    }
                    return SaleMapper.toResponseDTO(sale, cart);
                })
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SaleResponseDTO findById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con id: " + id));

        CartClientDTO cart = null;
        try {
            cart = shoppingCartFeignClient.getCartById(sale.getIdCart());
        } catch (Exception ignored) {
            log.warn("El microservicio shopping-cart-service está temporalmente fuera de línea. Retornando venta {} sin información del carrito.", sale.getId());
        }

        return SaleMapper.toResponseDTO(sale, cart);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!saleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Venta no encontrada con id: " + id);
        }
        saleRepository.deleteById(id);
        log.info("Venta eliminada exitosamente con ID: {}", id);
    }
}
