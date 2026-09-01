package com.emanueltito.products_service.controller;

import com.emanueltito.products_service.dto.ProductRequestDTO;
import com.emanueltito.products_service.dto.ProductResponseDTO;
import com.emanueltito.products_service.service.IProductService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Products", description = "Operations related to product management")
public class ProductController {

    private final IProductService productService;

    @Operation(summary = "Create a new product", description = "Saves a new product into the database and returns the created entity")
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO requestDTO) {
        log.info("Recibida petición para crear producto: {}", requestDTO.getName());
        ProductResponseDTO responseDTO = productService.save(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(summary = "Get all products", description = "Returns a list of all available products in the catalog")
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Get product by ID", description = "Returns a single product matching the specified ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        ProductResponseDTO product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @Operation(summary = "Get product by Code", description = "Returns a single product matching the specified 10-character code")
    @GetMapping("/code/{code}")
    public ResponseEntity<ProductResponseDTO> getProductByCode(@PathVariable String code) {
        ProductResponseDTO product = productService.findByCode(code);
        return ResponseEntity.ok(product);
    }

    @Operation(summary = "Get products by multiple Codes", description = "Returns a list of products that match the provided list of codes")
    @PostMapping("/by-codes")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCodes(@RequestBody List<String> codes) {
        List<ProductResponseDTO> products = productService.findByCodes(codes);
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Update an existing product", description = "Updates the fields of a product identified by its ID")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO requestDTO) {
        log.info("Recibida petición para actualizar producto con ID: {}", id);
        ProductResponseDTO updatedProduct = productService.update(id, requestDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @Operation(summary = "Delete a product", description = "Deletes a product from the database using its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.info("Recibida petición para eliminar producto con ID: {}", id);
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
