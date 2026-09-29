package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearEjemplarRequest {

    @NotBlank(message = "El código del ejemplar es obligatorio")
    private String codigo;

    @NotNull(message = "El ID del libro es obligatorio")
    private Long idLibro;
}