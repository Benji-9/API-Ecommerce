package com.uade.tpo.ecommerce.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    // Definimos todos los campos que el usuario completa en el formulario de registro
    private String nombreUsuario;
    private String email;
    private String password;
    private String nombre;
    private String apellido;
}