package com.emanueltito.products_service.service.impl;

import com.emanueltito.products_service.dto.ProductRequestDTO;
import com.emanueltito.products_service.dto.ProductResponseDTO;
import com.emanueltito.products_service.exception.DuplicateResourceException;
import com.emanueltito.products_service.exception.ResourceNotFoundException;
import com.emanueltito.products_service.mapper.ProductMapper;
import com.emanueltito.products_service.model.Product;
import com.emanueltito.products_service.repository.ProductRepository;
import com.emanueltito.products_service.service.IProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductResponseDTO save(ProductRequestDTO requestDTO) {
        if (productRepository.existsByCode(requestDTO.getCode())) {
            throw new DuplicateResourceException("Ya existe un producto con el código: " + requestDTO.getCode());
        }
        Product product = ProductMapper.toEntity(requestDTO);
        Product savedProduct = productRepository.save(product);
        log.info("Producto guardado exitosamente con ID: {}", savedProduct.getId());
        return ProductMapper.toResponseDTO(savedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
        return ProductMapper.toResponseDTO(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDTO findByCode(String code) {
        Product product = productRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con código: " + code));
        return ProductMapper.toResponseDTO(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> findByCodes(List<String> codes) {
        if (codes == null || codes.isEmpty()) {
            return List.of();
        }
        return productRepository.findByCodeIn(codes)
                .stream()
                .map(ProductMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public ProductResponseDTO update(Long id, ProductRequestDTO requestDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));

        if (!product.getCode().equals(requestDTO.getCode()) && productRepository.existsByCode(requestDTO.getCode())) {
            throw new DuplicateResourceException("Ya existe un producto con el código: " + requestDTO.getCode());
        }

        product.setCode(requestDTO.getCode());
        product.setName(requestDTO.getName());
        product.setBrand(requestDTO.getBrand());
        product.setIndividualPrice(requestDTO.getIndividualPrice());

        Product updatedProduct = productRepository.save(product);
        log.info("Producto actualizado exitosamente con ID: {}", updatedProduct.getId());
        return ProductMapper.toResponseDTO(updatedProduct);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado con id: " + id);
        }
        productRepository.deleteById(id);
        log.info("Producto eliminado exitosamente con ID: {}", id);
    }
}
