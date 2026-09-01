package com.emanueltito.sales_service.service;

import com.emanueltito.sales_service.dto.SaleRequestDTO;
import com.emanueltito.sales_service.dto.SaleResponseDTO;
import java.util.List;

public interface ISaleService {

    SaleResponseDTO save(SaleRequestDTO requestDTO);

    List<SaleResponseDTO> findAll();

    SaleResponseDTO findById(Long id);

    void delete(Long id);
}
