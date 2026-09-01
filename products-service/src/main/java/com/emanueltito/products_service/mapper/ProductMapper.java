package com.emanueltito.products_service.mapper;

import com.emanueltito.products_service.dto.ProductRequestDTO;
import com.emanueltito.products_service.dto.ProductResponseDTO;
import com.emanueltito.products_service.model.Product;

public final class ProductMapper {

    private ProductMapper() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no puede ser instanciada");
    }

    public static Product toEntity(ProductRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }

        return Product.builder()
                .code(requestDTO.getCode())
                .name(requestDTO.getName())
                .brand(requestDTO.getBrand())
                .individualPrice(requestDTO.getIndividualPrice())
                .build();
    }

    public static ProductResponseDTO toResponseDTO(Product product) {
        if (product == null) {
            return null;
        }

        return ProductResponseDTO.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .brand(product.getBrand())
                .individualPrice(product.getIndividualPrice())
                .build();
    }
}
