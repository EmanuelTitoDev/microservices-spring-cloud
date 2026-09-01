package com.emanueltito.sales_service.dto;

import com.emanueltito.sales_service.client.dto.CartClientDTO;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Data Transfer Object for sale responses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleResponseDTO {

    @Schema(description = "Database unique identifier", example = "1")
    private Long id;
    @Schema(description = "Date and time when the sale was created")
    private LocalDateTime date;
    @Schema(description = "ID of the associated shopping cart", example = "1")
    private Long idCart;
    @Schema(description = "Detailed information about the shopping cart")
    private CartClientDTO cart;
}
