package com.emanueltito.products_service.dto;

import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Data Transfer Object for product responses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {

    @Schema(description = "Database unique identifier", example = "1")
    private Long id;
    @Schema(description = "Unique 10-character code for the product", example = "PROD000001")
    private String code;
    @Schema(description = "Name of the product", example = "Wireless Mouse")
    private String name;
    @Schema(description = "Brand of the product", example = "Logitech")
    private String brand;
    @Schema(description = "Price of a single unit of the product", example = "29.99")
    private BigDecimal individualPrice;
}
