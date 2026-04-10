package com.uade.tpo.ecommerce.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

// DTO para enviar datos limpios al Frontend
@Data
@Builder
public class ProductoResponseDTO {
    private Long id;
    private String nombre;
    private double precio;
    private String descripcion;
    private Integer stock;
    private List<String> imagenesUrl;
    private List<String> categorias; // Solo enviamos los nombres de las categorías
}