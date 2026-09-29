package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearLibroRequest {
    @NotBlank(message = "El título es obligatorio")
    @Size(max = 150, message = "El título no puede superar los 150 caracteres")
    private String titulo;

    @NotBlank(message = "El ISBN es obligatorio")
    @Size(max = 20, message = "El ISBN no puede superar los 20 caracteres")
    private String isbn;

    @NotBlank(message = "La categoría es obligatoria")
    @Size(max = 80, message = "La categoría no puede superar los 80 caracteres")
    private String categoria;

    @NotNull(message = "El año de publicación es obligatorio")
    private Integer anioPublicacion;

    @NotBlank(message = "La editorial es obligatoria")
    @Size(max = 80, message = "La editorial no puede superar los 80 caracteres")
    private String editorial;
}
