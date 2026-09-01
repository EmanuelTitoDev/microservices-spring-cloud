package com.emanueltito.sales_service.mapper;

import com.emanueltito.sales_service.client.dto.CartClientDTO;
import com.emanueltito.sales_service.dto.SaleRequestDTO;
import com.emanueltito.sales_service.dto.SaleResponseDTO;
import com.emanueltito.sales_service.model.Sale;
import java.time.LocalDateTime;

public final class SaleMapper {

    private SaleMapper() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no puede ser instanciada");
    }

    public static Sale toEntity(SaleRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }

        return Sale.builder()
                .date(LocalDateTime.now())
                .idCart(requestDTO.getIdCart())
                .build();
    }

    public static SaleResponseDTO toResponseDTO(Sale sale) {
        return toResponseDTO(sale, null);
    }

    public static SaleResponseDTO toResponseDTO(Sale sale, CartClientDTO cart) {
        if (sale == null) {
            return null;
        }

        return SaleResponseDTO.builder()
                .id(sale.getId())
                .date(sale.getDate())
                .idCart(sale.getIdCart())
                .cart(cart)
                .build();
    }
}
