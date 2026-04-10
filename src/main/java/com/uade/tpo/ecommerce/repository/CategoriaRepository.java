package com.uade.tpo.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uade.tpo.ecommerce.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
