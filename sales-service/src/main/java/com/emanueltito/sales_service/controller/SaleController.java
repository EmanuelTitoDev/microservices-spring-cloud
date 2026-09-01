package com.emanueltito.sales_service.controller;

import com.emanueltito.sales_service.dto.SaleRequestDTO;
import com.emanueltito.sales_service.dto.SaleResponseDTO;
import com.emanueltito.sales_service.service.ISaleService;
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
@RequestMapping("/api/sales")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Sales", description = "Operations related to sales management")
public class SaleController {

    private final ISaleService saleService;

    @Operation(summary = "Create a new sale", description = "Creates a sale record linked to an existing shopping cart ID")
    @PostMapping
    public ResponseEntity<SaleResponseDTO> createSale(@Valid @RequestBody SaleRequestDTO requestDTO) {
        log.info("Recibida petición para crear una nueva venta");
        SaleResponseDTO responseDTO = saleService.save(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(summary = "Get all sales", description = "Retrieves a list of all existing sales records in the system")
    @GetMapping
    public ResponseEntity<List<SaleResponseDTO>> getAllSales() {
        List<SaleResponseDTO> sales = saleService.findAll();
        return ResponseEntity.ok(sales);
    }

    @Operation(summary = "Get a sale by ID", description = "Retrieves the details of a specific sale matching the provided ID")
    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> getSaleById(@PathVariable Long id) {
        SaleResponseDTO sale = saleService.findById(id);
        return ResponseEntity.ok(sale);
    }

    @Operation(summary = "Delete a sale", description = "Deletes an existing sale record based on its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        log.info("Recibida petición para eliminar venta con ID: {}", id);
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
