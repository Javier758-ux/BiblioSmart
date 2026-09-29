package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CrearReservaRequest {
    @NotNull(message = "El ID del lector es obligatorio")
    private Long idLector;

    @NotNull(message = "El ID del libro es obligatorio")
    private Long idLibro;
}
