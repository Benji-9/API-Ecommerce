package com.uade.tpo.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.model.Pedido;
import com.uade.tpo.ecommerce.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido getPedidoById(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public void deletePedidoById(Long id) {
        pedidoRepository.deleteById(id);
    }

    public Pedido savePedido(Pedido pedido) {
        return pedidoRepository.save(pedido);

    }

    public Pedido updatePedido(Long id, Pedido pedido) {
        Pedido existingPedido = getPedidoById(id);
        if (existingPedido != null) {
            existingPedido.setProductos(pedido.getProductos());
            existingPedido.setEstado(pedido.getEstado());
            existingPedido.setUsuario(pedido.getUsuario());
            return pedidoRepository.save(existingPedido);
        }
        return null;
    }
}
