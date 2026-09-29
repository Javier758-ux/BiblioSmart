package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CrearAutorRequest {
    @NotBlank(message = "El nombre del autor es obligatorio")
    private String nombre;
}
