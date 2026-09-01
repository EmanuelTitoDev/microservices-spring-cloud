package com.emanueltito.products_service.model;

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
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "El código no puede estar vacío")
    @Size(min = 10, max = 10, message = "El código debe tener exactamente 10 caracteres")
    @Column(name = "codigo", nullable = false, length = 10, unique = true)
    private String code;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(name = "nombre", nullable = false, length = 100)
    private String name;

    @NotBlank(message = "La marca no puede estar vacía")
    @Size(min = 2, max = 100, message = "La marca debe tener entre 2 y 100 caracteres")
    @Column(name = "marca", nullable = false, length = 100)
    private String brand;

    @NotNull(message = "El precio individual es obligatorio")
    @Positive(message = "El precio individual debe ser mayor que cero")
    @Column(name = "precio_individual", nullable = false)
    private BigDecimal individualPrice;
}
