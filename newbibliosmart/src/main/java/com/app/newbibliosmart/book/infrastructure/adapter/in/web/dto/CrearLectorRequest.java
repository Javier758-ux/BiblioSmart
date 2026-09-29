package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter

public class CrearLectorRequest {
    @NotBlank(message = "El número de lector es obligatorio")
    private String nroLector;

    @NotBlank(message = "Los datos de contacto son obligatorios")
    private String datosContacto;
    @NotNull(message = "El usuario es obligatorio")
    private Long idUsuario;
}
