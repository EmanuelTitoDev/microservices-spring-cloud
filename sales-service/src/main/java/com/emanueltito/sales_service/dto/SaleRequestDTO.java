package com.emanueltito.sales_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Data Transfer Object for creating a new sale")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleRequestDTO {

    @NotNull(message = "El ID del carrito es obligatorio")
    @Positive(message = "El ID del carrito debe ser mayor que cero")
    @Schema(description = "ID of the shopping cart associated with this sale", example = "1")
    private Long idCart;
}
