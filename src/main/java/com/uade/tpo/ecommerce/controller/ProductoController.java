package com.uade.tpo.ecommerce.controller;

import com.uade.tpo.ecommerce.model.Producto;
import com.uade.tpo.ecommerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /**
     * LISTAR PRODUCTOS (Ordenados alfabéticamente)
     * URL: GET http://localhost:8081/api/productos
     */
    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.getAllProductos();
        return ResponseEntity.ok(productos);
    }

    /**
     * BUSCAR POR ID (Detalle del producto)
     * URL: GET http://localhost:8081/api/productos/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.getProductoById(id));
    }

    /**
     * ALTA DE PRODUCTO
     * URL: POST http://localhost:8081/api/productos
     * Body: JSON con datos del producto
     */
    @PostMapping
    public ResponseEntity<Producto> saveProducto(@RequestBody Producto producto) {
        Producto nuevo = productoService.saveProducto(producto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    /**
     * EDITAR PRODUCTO
     * URL: PUT http://localhost:8081/api/productos/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.updateProducto(id, producto));
    }

    /**
     * ELIMINAR PRODUCTO
     * URL: DELETE http://localhost:8081/api/productos/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductoById(@PathVariable Long id) {
        productoService.deleteProductoById(id);
        return ResponseEntity.noContent().build();
    }
}