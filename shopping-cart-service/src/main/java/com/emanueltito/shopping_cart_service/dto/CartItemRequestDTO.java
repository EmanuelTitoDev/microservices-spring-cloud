package com.emanueltito.shopping_cart_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Data Transfer Object representing a product item within a shopping cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemRequestDTO {

    @NotBlank(message = "El código del producto es obligatorio")
    @Size(min = 10, max = 10, message = "El código debe tener exactamente 10 caracteres")
    @Schema(description = "Unique 10-character code for the product", example = "PROD000001")
    private String productCode;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor que cero")
    @Schema(description = "Quantity of the product", example = "2")
    private Integer quantity;
}
