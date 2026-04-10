package com.uade.tpo.ecommerce.service;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uade.tpo.ecommerce.dto.LoginRequest;
import com.uade.tpo.ecommerce.dto.RegisterRequest;
import com.uade.tpo.ecommerce.model.Role;
import com.uade.tpo.ecommerce.model.Usuario;
import com.uade.tpo.ecommerce.repository.UsuarioRepository;
import com.uade.tpo.ecommerce.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import com.uade.tpo.ecommerce.dto.RegisterResponse;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public RegisterResponse register(RegisterRequest request) {
        // Validaciones de duplicados para evitar errores de base de datos
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya existe");
        }
        if (usuarioRepository.existsByNombreUsuario(request.getNombreUsuario())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        Usuario usuario = Usuario.builder()
                .nombreUsuario(request.getNombreUsuario())
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fechaNacimiento(request.getFechaNacimiento()) // Set fecha
                .sexo(request.getSexo()) // Set sexo
                .role(Role.USER)
                .build();

        usuarioRepository.save(usuario);
        // Devolvemos el objeto en lugar de un String plano
        return RegisterResponse.builder()
                .mensaje("Usuario registrado exitosamente")
                .nombreUsuario(usuario.getNombreUsuario())
                .email(usuario.getEmail())
                .build();
    }

    public String authenticate(LoginRequest request) {
        // Válida credenciales (email y password) contra la DB
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Usuario user = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
        Set<String> roles = user.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.toSet());

        // Genera el token JWT para que el cliente lo use en sus peticiones
        return jwtUtil.generateToken(user.getEmail(), roles);
    }
}