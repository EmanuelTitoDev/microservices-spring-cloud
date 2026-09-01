package com.emanueltito.products_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Data Transfer Object for creating or updating a Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDTO {

    @NotBlank(message = "El código no puede estar vacío")
    @Size(min = 10, max = 10, message = "El código debe tener exactamente 10 caracteres")
    @Schema(description = "Unique 10-character code for the product", example = "PROD000001")
    private String code;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Schema(description = "Name of the product", example = "Wireless Mouse")
    private String name;

    @NotBlank(message = "La marca no puede estar vacía")
    @Size(min = 2, max = 100, message = "La marca debe tener entre 2 y 100 caracteres")
    @Schema(description = "Brand of the product", example = "Logitech")
    private String brand;

    @NotNull(message = "El precio individual es obligatorio")
    @Positive(message = "El precio individual debe ser mayor que cero")
    @Schema(description = "Price of a single unit of the product", example = "29.99")
    private BigDecimal individualPrice;
}
