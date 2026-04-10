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
     * LISTAR PRODUCTOS
     * Puede recibir un parámetro opcional 'categoriaId' para filtrar.
     * URL: GET http://localhost:8081/api/productos
     * URL con Filtro: GET http://localhost:8081/api/productos?categoriaId=1
     */
    @GetMapping
    public ResponseEntity<List<Producto>> getProductos(@RequestParam(required = false) Long categoriaId) {
        if (categoriaId != null) {
            return ResponseEntity.ok(productoService.getProductosByCategoria(categoriaId));
        }
        return ResponseEntity.ok(productoService.getAllProductos());
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