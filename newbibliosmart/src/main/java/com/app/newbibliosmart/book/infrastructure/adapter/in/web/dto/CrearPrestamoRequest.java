package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearPrestamoRequest {
    @NotNull
    private Long idLector;
    @NotNull
    private Long idEjemplar;
}