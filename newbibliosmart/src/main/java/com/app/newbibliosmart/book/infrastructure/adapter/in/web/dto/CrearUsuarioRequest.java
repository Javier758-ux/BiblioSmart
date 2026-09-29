package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CrearUsuarioRequest {
    @NotNull
    private Long idRol;
    @NotBlank
    private String nombreCompleto;
    @NotBlank
    private String credenciales;
    @NotBlank
    private String estado;
}