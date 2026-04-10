package com.uade.tpo.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Excepción genérica para cuando no se encuentra un recurso (404)
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String recurso) {
        super(recurso + " con ID " + id + " no encontrado/a.");
    }
}