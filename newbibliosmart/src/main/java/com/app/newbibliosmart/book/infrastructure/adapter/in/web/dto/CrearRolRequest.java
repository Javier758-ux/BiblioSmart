package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CrearRolRequest {
    @NotBlank
    private String nombreRol;
    private String descripcion;
}