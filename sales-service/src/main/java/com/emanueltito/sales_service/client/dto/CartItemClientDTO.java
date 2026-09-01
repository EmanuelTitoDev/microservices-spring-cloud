package com.emanueltito.sales_service.client.dto;

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
public class CartItemClientDTO {

    private Long id;
    private String productCode;
    private String productName;
    private String productBrand;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal subtotal;
}
