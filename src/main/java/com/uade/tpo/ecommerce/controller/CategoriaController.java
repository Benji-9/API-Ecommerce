package com.uade.tpo.ecommerce.controller;

import com.uade.tpo.ecommerce.dto.CategoriaDTO;
import com.uade.tpo.ecommerce.model.Categoria;
import com.uade.tpo.ecommerce.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    /**
     * LISTAR CATEGORÍAS (Requerido para la Home del TPO)
     * URL: GET http://localhost:8081/api/categorias
     */
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> getAllCategorias() {
        return ResponseEntity.ok(categoriaService.getAllCategorias());
    }

    /**
     * OBTENER CATEGORÍA POR ID
     * URL: GET http://localhost:8081/api/categorias/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.getCategoriaById(id));
    }

    /**
     * CREAR CATEGORÍA
     * URL: POST http://localhost:8081/api/categorias
     * Body JSON: { "nombre": "Electrónica" }
     */
    @PostMapping
    public ResponseEntity<CategoriaDTO> saveCategoria(@RequestBody Categoria categoria) {
        return new ResponseEntity<>(categoriaService.saveCategoria(categoria), HttpStatus.CREATED);
    }

    /**
     * ELIMINAR CATEGORÍA
     * URL: DELETE http://localhost:8081/api/categorias/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }
}