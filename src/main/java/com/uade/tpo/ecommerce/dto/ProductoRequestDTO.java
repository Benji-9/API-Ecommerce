package com.uade.tpo.ecommerce.dto;

import lombok.Data;
import java.util.List;

// DTO para recibir datos del Frontend al crear/editar productos
@Data
public class ProductoRequestDTO {
    private String nombre;
    private double precio;
    private String descripcion;
    private Integer stock;
    private List<String> imagenesUrl;
    private List<Long> categoriaIds; // Recibimos ID de categorías
}