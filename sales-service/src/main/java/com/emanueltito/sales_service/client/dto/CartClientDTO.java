package com.emanueltito.sales_service.client.dto;

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
public class CartClientDTO {

    private Long id;
    private BigDecimal totalPrice;
    private List<CartItemClientDTO> items;
}
