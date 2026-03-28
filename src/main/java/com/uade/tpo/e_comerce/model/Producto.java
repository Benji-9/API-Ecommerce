package com.uade.tpo.e_comerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Data;

@Entity
@Table(name = "productos")
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    private int precio;

    // Esto NO anda, consultar :D
    @jakarta.persistence.ManyToMany(fetch = jakarta.persistence.FetchType.LAZY)
    @jakarta.persistence.JoinTable(name = "productos_categorias", joinColumns = @jakarta.persistence.JoinColumn(name = "producto_id"), inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "categoria_id"))
    private java.util.List<Categoria> categorias = new java.util.ArrayList<>();

}
