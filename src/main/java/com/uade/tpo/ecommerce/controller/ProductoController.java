package com.uade.tpo.ecommerce.controller;

import com.uade.tpo.ecommerce.dto.ProductoRequestDTO;
import com.uade.tpo.ecommerce.dto.ProductoResponseDTO;
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

    // GET http://localhost:8081/api/productos -> Devuelve lista de ResponseDTO
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> getProductos(@RequestParam(required = false) Long categoriaId) {
        if (categoriaId != null) {
            return ResponseEntity.ok(productoService.getProductosByCategoriaDTO(categoriaId));
        }
        return ResponseEntity.ok(productoService.getAllProductosDTO());
    }

    // GET http://localhost:8081/api/productos/{id} -> Detalle del producto en DTO
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> getProductoById(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.getProductoDTOById(id));
    }

    // POST http://localhost:8081/api/productos -> Recibe RequestDTO, devuelve ResponseDTO
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> saveProducto(@RequestBody ProductoRequestDTO request) {
        return new ResponseEntity<>(productoService.crearProducto(request), HttpStatus.CREATED);
    }

    // PUT http://localhost:8081/api/productos/{id} -> Actualización mediante DTO
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> updateProducto(@PathVariable Long id, @RequestBody ProductoRequestDTO request) {
        return ResponseEntity.ok(productoService.updateProductoFromDTO(id, request));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductoById(@PathVariable Long id) {
        productoService.deleteProductoById(id);
        return ResponseEntity.noContent().build();
    }
}