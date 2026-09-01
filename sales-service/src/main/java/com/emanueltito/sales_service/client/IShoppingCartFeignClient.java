package com.emanueltito.sales_service.client;

import com.emanueltito.sales_service.client.dto.CartClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "shopping-cart-service", path = "/api/cart")
public interface IShoppingCartFeignClient {

    @GetMapping("/{id}")
    CartClientDTO getCartById(@PathVariable("id") Long id);
}
