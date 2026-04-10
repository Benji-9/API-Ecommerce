package com.uade.tpo.ecommerce.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uade.tpo.ecommerce.model.Producto;
import com.uade.tpo.ecommerce.repository.ProductoRepository;
import com.uade.tpo.ecommerce.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAllByOrderByNombreAsc();
    }

    // Nuevo metodo para filtrar por categoría
    public List<Producto> getProductosByCategoria(Long categoriaId) {
        return productoRepository.findByCategorias_IdOrderByNombreAsc(categoriaId);
    }

    public Producto getProductoById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto con ID " + id + " no encontrado"));
    }

    public void deleteProductoById(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar: Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Long id, Producto productoDetails) {
        Producto producto = getProductoById(id);
        producto.setNombre(productoDetails.getNombre());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setDescripcion(productoDetails.getDescripcion());
        producto.setStock(productoDetails.getStock());
        producto.setImagenesUrl(productoDetails.getImagenesUrl()); // Actualizamos lista de fotos
        return productoRepository.save(producto);
    }
}