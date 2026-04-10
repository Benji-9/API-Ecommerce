package com.uade.tpo.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.model.Usuario;
import com.uade.tpo.ecommerce.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void deleteUsuarioById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);

    }

    public Usuario updateUsuario(Long id, Usuario usuario) {
        Usuario existingUsuario = getUsuarioById(id);
        if (existingUsuario != null) {
            existingUsuario.setNombre(usuario.getNombre());
            existingUsuario.setApellido(usuario.getApellido());
            existingUsuario.setEmail(usuario.getEmail());
            return usuarioRepository.save(existingUsuario);
        }
        return null;
    }
}
