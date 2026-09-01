package com.emanueltito.products_service.service;

import com.emanueltito.products_service.dto.ProductRequestDTO;
import com.emanueltito.products_service.dto.ProductResponseDTO;
import java.util.List;

public interface IProductService {

    ProductResponseDTO save(ProductRequestDTO requestDTO);

    List<ProductResponseDTO> findAll();

    ProductResponseDTO findById(Long id);

    ProductResponseDTO findByCode(String code);

    List<ProductResponseDTO> findByCodes(List<String> codes);

    ProductResponseDTO update(Long id, ProductRequestDTO requestDTO);

    void delete(Long id);
}
