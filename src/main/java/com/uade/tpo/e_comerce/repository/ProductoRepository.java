package com.uade.tpo.e_comerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uade.tpo.e_comerce.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
