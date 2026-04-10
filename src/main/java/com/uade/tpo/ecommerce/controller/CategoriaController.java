package com.uade.tpo.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.ecommerce.model.Categoria;
import com.uade.tpo.ecommerce.service.CategoriaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

// La api para productos con los endpoints para crear, editar, eliminar y listar productos
// http://localhost:8080/api/productos Listar productos
// http://localhost:8080/api/productos/1 Buscar productos por ID
// http://localhost:8080/api/productosEditar producto
// http://localhost:8080/api/productosEliminar producto
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    // http://localhost:8080/api/categorias -> devuelve la lista de categorias
    @GetMapping
    public List<Categoria> getAllCategorias() {
        return categoriaService.getAllCategorias();
    }

    // http://localhost:8080/api/categorias/1 -> devuelve la categoria con id 1
    @GetMapping("/{id}")
    public Categoria getCategoriaById(@PathVariable Long id) {
        return categoriaService.getCategoriaById(id);
    }

    // del http://localhost:8080/api/categorias/1 -> elimina la categoria con id 1
    @DeleteMapping("/{id}")
    public void deleteProductoById(@PathVariable Long id) {
        categoriaService.deleteCategoriaById(id);
    }

    @PostMapping
    public Categoria saveCategoria(@RequestBody Categoria categoria) {
        return categoriaService.saveCategoria(categoria);

    }

    @PutMapping("/{id}")
    public Categoria udpateCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        return categoriaService.updateCategoria(id, categoria);
    }

}
