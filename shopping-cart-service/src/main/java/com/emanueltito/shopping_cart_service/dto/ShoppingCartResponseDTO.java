package com.emanueltito.shopping_cart_service.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCartResponseDTO {

    private Long id;
    private BigDecimal totalPrice;
    private List<CartItemResponseDTO> items;
}
