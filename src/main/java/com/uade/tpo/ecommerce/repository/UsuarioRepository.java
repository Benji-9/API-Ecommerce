package com.uade.tpo.ecommerce.repository;

import com.uade.tpo.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    // Metodo para validar duplicados en el registro
    boolean existsByEmail(String email);

    // Metodo para validar si el nombre de usuario ya está tomado
    boolean existsByNombreUsuario(String nombreUsuario);
}