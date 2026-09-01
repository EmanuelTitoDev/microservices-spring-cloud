package com.emanueltito.shopping_cart_service.mapper;

import com.emanueltito.shopping_cart_service.dto.CartItemResponseDTO;
import com.emanueltito.shopping_cart_service.dto.ShoppingCartResponseDTO;
import com.emanueltito.shopping_cart_service.model.ShoppingCart;
import java.util.List;

public final class ShoppingCartMapper {

    private ShoppingCartMapper() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no puede ser instanciada");
    }

    public static ShoppingCartResponseDTO toResponseDTO(ShoppingCart cart) {
        if (cart == null) {
            return null;
        }

        List<CartItemResponseDTO> itemDTOs = cart.getItems() != null
                ? cart.getItems().stream().map(CartItemMapper::toResponseDTO).toList()
                : List.of();

        return ShoppingCartResponseDTO.builder()
                .id(cart.getId())
                .totalPrice(cart.getTotalPrice())
                .items(itemDTOs)
                .build();
    }
}
