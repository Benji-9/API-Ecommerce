package com.uade.tpo.e_comerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.e_comerce.model.Usuario;
import com.uade.tpo.e_comerce.service.UsuarioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

// La api para productos con los endpoints para crear, editar, eliminar y listar productos
// http://localhost:8080/api/productos Listar productos
// http://localhost:8080/api/productos/1 Buscar productos por ID
// http://localhost:8080/api/productosEditar producto
// http://localhost:8080/api/productosEliminar producto
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    // http://localhost:8080/api/usuarios -> devuelve la lista de usuarios
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    // http://localhost:8080/api/usuarios/1 -> devuelve el usuario con id 1
    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id);
    }

    // del http://localhost:8080/api/usuarios/1 -> elimina el usuario con id 1
    @DeleteMapping("/{id}")
    public void deleteUsuarioById(@PathVariable Long id) {
        usuarioService.deleteUsuarioById(id);
    }

    @PostMapping
    public Usuario saveUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);

    }

    @PutMapping("/{id}")
    public Usuario udpateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(id, usuario);
    }

}
