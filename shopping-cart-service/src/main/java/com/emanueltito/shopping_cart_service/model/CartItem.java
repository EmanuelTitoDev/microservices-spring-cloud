package com.emanueltito.shopping_cart_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items_carrito")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El código del producto no puede estar vacío")
    @Size(min = 10, max = 10, message = "El código debe tener exactamente 10 caracteres")
    @Column(name = "codigo_producto", nullable = false, length = 10)
    private String productCode;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(name = "nombre_producto", nullable = false, length = 100)
    private String productName;

    @NotBlank(message = "La marca no puede estar vacía")
    @Size(min = 2, max = 100, message = "La marca debe tener entre 2 y 100 caracteres")
    @Column(name = "marca_producto", nullable = false, length = 100)
    private String productBrand;

    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio unitario debe ser mayor que cero")
    @Column(name = "precio_unitario", nullable = false)
    private BigDecimal unitPrice;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor que cero")
    @Column(name = "cantidad", nullable = false)
    private Integer quantity;

    @NotNull(message = "El subtotal es obligatorio")
    @Positive(message = "El subtotal debe ser mayor que cero")
    @Column(name = "subtotal", nullable = false)
    private BigDecimal subtotal;
}
