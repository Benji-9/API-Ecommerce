package com.uade.tpo.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.model.Categoria;
import com.uade.tpo.ecommerce.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria getCategoriaById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public void deleteCategoriaById(Long id) {
        categoriaRepository.deleteById(id);
    }

    public Categoria saveCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);

    }

    public Categoria updateCategoria(Long id, Categoria categoria) {
        Categoria existingCategoria = getCategoriaById(id);
        if (existingCategoria != null) {
            existingCategoria.setNombre(categoria.getNombre());
            return categoriaRepository.save(existingCategoria);
        }
        return null;
    }
}
