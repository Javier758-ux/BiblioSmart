package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CrearLibroAutorRequest {
    @NotNull(message = "El ID del libro es obligatorio")
    private Long idLibro;

    @NotNull(message = "El ID del autor es obligatorio")
    private Long idAutor;
}
