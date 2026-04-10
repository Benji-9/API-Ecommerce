package com.uade.tpo.ecommerce.service;

import com.uade.tpo.ecommerce.dto.ProductoRequestDTO;
import com.uade.tpo.ecommerce.dto.ProductoResponseDTO;
import com.uade.tpo.ecommerce.exception.BadRequestException;
import com.uade.tpo.ecommerce.exception.ResourceNotFoundException;
import com.uade.tpo.ecommerce.model.Categoria;
import com.uade.tpo.ecommerce.model.Producto;
import com.uade.tpo.ecommerce.repository.CategoriaRepository;
import com.uade.tpo.ecommerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Métodos para listar usando DTO
    public List<ProductoResponseDTO> getAllProductosDTO() {
        return productoRepository.findAllByOrderByNombreAsc().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ProductoResponseDTO> getProductosByCategoriaDTO(Long categoriaId) {
        return productoRepository.findByCategorias_IdOrderByNombreAsc(categoriaId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductoResponseDTO getProductoDTOById(Long id) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", id));
        return convertToDTO(p);
    }

    /**
     * Lógica para CREAR un producto validando datos.
     */
    public ProductoResponseDTO crearProducto(ProductoRequestDTO request) {
        // Validación de negocio (Punto del profesor: ArgumentInvalidException)
        if (request.getPrecio() < 0) {
            throw new BadRequestException("El precio no puede ser negativo.");
        }
        if (request.getNombre() == null || request.getNombre().isEmpty()) {
            throw new BadRequestException("El nombre del producto es obligatorio.");
        }

        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setPrecio(request.getPrecio());
        producto.setDescripcion(request.getDescripcion());
        producto.setStock(request.getStock());
        producto.setImagenesUrl(request.getImagenesUrl());

        // Buscamos las categorías por ID para vincularlas
        if (request.getCategoriaIds() != null) {
            List<Categoria> categorias = categoriaRepository.findAllById(request.getCategoriaIds());
            producto.setCategorias(categorias);
        }

        return convertToDTO(productoRepository.save(producto));
    }

    /**
     * Lógica para ACTUALIZAR un producto.
     */
    public ProductoResponseDTO updateProductoFromDTO(Long id, ProductoRequestDTO request) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", id));

        producto.setNombre(request.getNombre());
        producto.setPrecio(request.getPrecio());
        producto.setDescripcion(request.getDescripcion());
        producto.setStock(request.getStock());
        producto.setImagenesUrl(request.getImagenesUrl());

        return convertToDTO(productoRepository.save(producto));
    }

    public void deleteProductoById(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto", id);
        }
        productoRepository.deleteById(id);
    }

    // Helper para transformar Entidad -> ResponseDTO
    private ProductoResponseDTO convertToDTO(Producto p) {
        return ProductoResponseDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .precio(p.getPrecio())
                .descripcion(p.getDescripcion())
                .stock(p.getStock())
                .imagenesUrl(p.getImagenesUrl())
                .categorias(p.getCategorias().stream().map(Categoria::getNombre).collect(Collectors.toList()))
                .build();
    }
}