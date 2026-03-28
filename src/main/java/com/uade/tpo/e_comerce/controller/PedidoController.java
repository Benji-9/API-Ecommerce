package com.uade.tpo.e_comerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.e_comerce.model.Pedido;
import com.uade.tpo.e_comerce.service.PedidoService;

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
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    // http://localhost:8080/api/productos -> devuelve la lista de productos
    @GetMapping
    public List<Pedido> getAllPedidos() {
        return pedidoService.getAllPedidos();
    }

    // http://localhost:8080/api/productos/1 -> devuelve el producto con id 1
    @GetMapping("/{id}")
    public Pedido getPedidoById(@PathVariable Long id) {
        return pedidoService.getPedidoById(id);
    }

    // del http://localhost:8080/api/productos/1 -> elimina el producto con id 1
    @DeleteMapping("/{id}")
    public void deleteProductoById(@PathVariable Long id) {
        pedidoService.deletePedidoById(id);
    }

    @PostMapping
    public Pedido savePedido(@RequestBody Pedido pedido) {
        return pedidoService.savePedido(pedido);

    }

    @PutMapping("/{id}")
    public Pedido udpatePedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        return pedidoService.updatePedido(id, pedido);
    }

}
