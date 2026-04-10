package com.uade.tpo.ecommerce.controller;

import com.uade.tpo.ecommerce.model.Pedido;
import com.uade.tpo.ecommerce.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // GET /api/pedidos — listar todos los pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        return ResponseEntity.ok(pedidoService.getAllPedidos());
    }

    // POST /api/pedidos/checkout — realizar la compra
    // Descuenta stock y calcula el total
    @PostMapping("/checkout")
    public ResponseEntity<Pedido> realizarCheckout(@RequestBody Pedido pedido) {
        return new ResponseEntity<>(pedidoService.realizarCheckout(pedido), HttpStatus.CREATED);
    }

    // DELETE /api/pedidos/{id} — eliminar un pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedidoById(@PathVariable Long id) {
        pedidoService.deletePedidoById(id);
        return ResponseEntity.noContent().build();
    }
}