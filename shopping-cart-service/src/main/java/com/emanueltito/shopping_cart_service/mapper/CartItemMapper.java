package com.emanueltito.shopping_cart_service.mapper;

import com.emanueltito.shopping_cart_service.client.dto.ProductClientDTO;
import com.emanueltito.shopping_cart_service.dto.CartItemResponseDTO;
import com.emanueltito.shopping_cart_service.model.CartItem;
import java.math.BigDecimal;

public final class CartItemMapper {

    private CartItemMapper() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no puede ser instanciada");
    }

    public static CartItem toEntity(ProductClientDTO productDTO, Integer quantity) {
        if (productDTO == null) {
            return null;
        }

        BigDecimal unitPrice = productDTO.getIndividualPrice();
        BigDecimal subtotal = unitPrice.multiply(BigDecimal.valueOf(quantity));

        return CartItem.builder()
                .productCode(productDTO.getCode())
                .productName(productDTO.getName())
                .productBrand(productDTO.getBrand())
                .unitPrice(unitPrice)
                .quantity(quantity)
                .subtotal(subtotal)
                .build();
    }

    public static CartItemResponseDTO toResponseDTO(CartItem item) {
        if (item == null) {
            return null;
        }

        return CartItemResponseDTO.builder()
                .id(item.getId())
                .productCode(item.getProductCode())
                .productName(item.getProductName())
                .productBrand(item.getProductBrand())
                .unitPrice(item.getUnitPrice())
                .quantity(item.getQuantity())
                .subtotal(item.getSubtotal())
                .build();
    }
}
