package com.uade.tpo.ecommerce.service;


import com.uade.tpo.ecommerce.exception.BadRequestException;
import com.uade.tpo.ecommerce.exception.ResourceNotFoundException;
import com.uade.tpo.ecommerce.model.Producto;
import com.uade.tpo.ecommerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uade.tpo.ecommerce.model.Pedido;
import com.uade.tpo.ecommerce.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProductoRepository productoRepository;

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido getPedidoById(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public void deletePedidoById(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Transactional // CRÍTICO para el checkout [cite: 630]
    public Pedido realizarCheckout(Pedido pedido) {
        double totalCalculado = 0;

        for (Producto p : pedido.getProductos()) {
            Producto dbProd = productoRepository.findById(p.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

            if (dbProd.getStock() <= 0) {
                throw new BadRequestException("El producto " + dbProd.getNombre() + " no tiene stock");
            }

            dbProd.setStock(dbProd.getStock() - 1); // Descontar stock [cite: 629]
            productoRepository.save(dbProd);

            totalCalculado += dbProd.getPrecio();
        }

        pedido.setTotal(totalCalculado); // Calcular costo total [cite: 628]
        pedido.setFecha(LocalDateTime.now());
        pedido.setEstado("COMPLETADO");
        return pedidoRepository.save(pedido);
    }

    public Pedido updatePedido(Long id, Pedido pedidoDetails) {
        Pedido pedido = getPedidoById(id);
        if (pedido != null) {
            pedido.setDetalle(pedidoDetails.getDetalle());
            pedido.setEstado(pedidoDetails.getEstado());
            return pedidoRepository.save(pedido);
        }
        return null;
    }
}