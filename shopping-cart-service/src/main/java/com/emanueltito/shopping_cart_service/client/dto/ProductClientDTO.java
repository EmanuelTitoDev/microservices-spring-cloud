package com.emanueltito.shopping_cart_service.client.dto;

import java.math.BigDecimal;
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
public class ProductClientDTO {

    private Long id;
    private String code;
    private String name;
    private String brand;
    private BigDecimal individualPrice;
}
