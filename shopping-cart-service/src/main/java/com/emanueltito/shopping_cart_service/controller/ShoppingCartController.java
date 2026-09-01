package com.emanueltito.shopping_cart_service.controller;

import com.emanueltito.shopping_cart_service.dto.ShoppingCartRequestDTO;
import com.emanueltito.shopping_cart_service.dto.ShoppingCartResponseDTO;
import com.emanueltito.shopping_cart_service.service.IShoppingCartService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Shopping Cart", description = "Operations related to shopping cart management")
public class ShoppingCartController {

    private final IShoppingCartService shoppingCartService;

    @Operation(summary = "Create a new shopping cart", description = "Creates a shopping cart with the specified list of products and their quantities")
    @PostMapping
    public ResponseEntity<ShoppingCartResponseDTO> createCart(@Valid @RequestBody ShoppingCartRequestDTO requestDTO) {
        log.info("Recibida petición para crear un nuevo carrito");
        ShoppingCartResponseDTO responseDTO = shoppingCartService.save(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(summary = "Get all shopping carts", description = "Retrieves a list of all existing shopping carts in the system")
    @GetMapping
    public ResponseEntity<List<ShoppingCartResponseDTO>> getAllCarts() {
        List<ShoppingCartResponseDTO> carts = shoppingCartService.findAll();
        return ResponseEntity.ok(carts);
    }

    @Operation(summary = "Get a shopping cart by ID", description = "Retrieves the details of a specific shopping cart matching the provided ID")
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartResponseDTO> getCartById(@PathVariable Long id) {
        ShoppingCartResponseDTO cart = shoppingCartService.findById(id);
        return ResponseEntity.ok(cart);
    }

    @Operation(summary = "Delete a shopping cart", description = "Deletes an existing shopping cart based on its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        log.info("Recibida petición para eliminar carrito con ID: {}", id);
        shoppingCartService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
