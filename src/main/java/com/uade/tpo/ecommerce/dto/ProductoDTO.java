package com.uade.tpo.ecommerce.dto;

import lombok.Data;
import java.util.List;

@Data
public class ProductoDTO {
    private Long id;
    private String nombre;
    private Double precio;
    private String descripcion;
    private Integer stock;
    private String imagenUrl;
    private List<String> categorias; // Solo los nombres de las categorías
}