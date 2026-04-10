package com.uade.tpo.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uade.tpo.ecommerce.model.Categoria;
import com.uade.tpo.ecommerce.dto.CategoriaDTO;
import com.uade.tpo.ecommerce.repository.CategoriaRepository;
import com.uade.tpo.ecommerce.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional // Garantiza integridad en la base de datos [cite: 107]
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Obtener todas las categorías y convertirlas a DTO
    public List<CategoriaDTO> getAllCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO getCategoriaById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + id));
        return convertToDTO(categoria);
    }

    public CategoriaDTO saveCategoria(Categoria categoria) {
        Categoria nueva = categoriaRepository.save(categoria);
        return convertToDTO(nueva);
    }

    public void deleteCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar: Categoría no encontrada");
        }
        categoriaRepository.deleteById(id);
    }

    // Helper para convertir Entidad a DTO
    private CategoriaDTO convertToDTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNombre(categoria.getNombre());
        return dto;
    }
}