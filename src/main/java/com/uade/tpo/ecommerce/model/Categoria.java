package com.uade.tpo.ecommerce.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    // @JsonIgnore evita el bucle infinito en la serialización JSON
    // @Getter/@Setter en lugar de @Data evita el StackOverflow en toString()
    @JsonIgnore
    @ManyToMany(mappedBy = "categorias")
    private List<Producto> productos = new ArrayList<>();
}