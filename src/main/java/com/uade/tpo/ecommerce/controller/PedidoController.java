package com.uade.tpo.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.*;
import com.uade.tpo.ecommerce.model.Pedido;
import com.uade.tpo.ecommerce.service.PedidoService;
import java.util.List;
import org.springframework.*;
import org.springframework.web.bind.annotation.*;

// La api para productos con los endpoints para crear, editar, eliminar y listar productos
// http://localhost:8080/api/productos Listar productos
// http://localhost:8080/api/productos/1 Buscar productos por ID
// http://localhost:8080/api/productosEditar producto
// http://localhost:8080/api/productosEliminar producto
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        return ResponseEntity.ok(pedidoService.getAllPedidos());
    }

    @PostMapping("/checkout") // Endpoint específico para la compra
    public ResponseEntity<Pedido> realizarCheckout(@RequestBody Pedido pedido) {
        return new ResponseEntity<>(pedidoService.realizarCheckout(pedido), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedidoById(@PathVariable Long id) { // Nombre corregido
        pedidoService.deletePedidoById(id);
        return ResponseEntity.noContent().build();
    }
}
