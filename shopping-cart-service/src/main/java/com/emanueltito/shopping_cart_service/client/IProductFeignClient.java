package com.emanueltito.shopping_cart_service.client;

import com.emanueltito.shopping_cart_service.client.dto.ProductClientDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "products-service", path = "/api/products")
public interface IProductFeignClient {

    @PostMapping("/by-codes")
    List<ProductClientDTO> getProductsByCodes(@RequestBody List<String> codes);
}
