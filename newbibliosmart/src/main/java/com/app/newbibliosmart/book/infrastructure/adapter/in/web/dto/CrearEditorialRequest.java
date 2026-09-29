package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CrearEditorialRequest {
    @NotBlank(message = "El nombre de la editorial es obligatorio")
    private String nombre;
}