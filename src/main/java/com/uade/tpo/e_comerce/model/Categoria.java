package com.uade.tpo.e_comerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.util.List;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import lombok.Data;

@Entity
@Table(name = "categorias")
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @ManyToMany(mappedBy = "categorias")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<Producto> productos = new ArrayList<>();

}
