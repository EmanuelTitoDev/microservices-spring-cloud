package com.emanueltito.shopping_cart_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Data Transfer Object for creating a new shopping cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCartRequestDTO {

    @NotEmpty(message = "El carrito debe contener al menos un producto")
    @Valid
    @Schema(description = "List of items to add to the shopping cart")
    private List<CartItemRequestDTO> items;
}
