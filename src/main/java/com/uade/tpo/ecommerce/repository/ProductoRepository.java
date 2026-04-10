package com.uade.tpo.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uade.tpo.ecommerce.model.Producto;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Genera automáticamente: SELECT * FROM productos ORDER BY nombre ASC
    List<Producto> findAllByOrderByNombreAsc();
}