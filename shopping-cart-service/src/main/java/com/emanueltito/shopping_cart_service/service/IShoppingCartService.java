package com.emanueltito.shopping_cart_service.service;

import com.emanueltito.shopping_cart_service.dto.ShoppingCartRequestDTO;
import com.emanueltito.shopping_cart_service.dto.ShoppingCartResponseDTO;
import java.util.List;

public interface IShoppingCartService {

    ShoppingCartResponseDTO save(ShoppingCartRequestDTO requestDTO);

    List<ShoppingCartResponseDTO> findAll();

    ShoppingCartResponseDTO findById(Long id);

    void delete(Long id);
}
