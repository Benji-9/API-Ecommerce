package com.uade.tpo.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uade.tpo.ecommerce.model.Producto;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Listado alfabético para la Home (Requerido TPO)
    List<Producto> findAllByOrderByNombreAsc();

    // Filtro por categoría (Requerido TPO: "información completa o filtrada")
    List<Producto> findByCategorias_IdOrderByNombreAsc(Long categoriaId);
}