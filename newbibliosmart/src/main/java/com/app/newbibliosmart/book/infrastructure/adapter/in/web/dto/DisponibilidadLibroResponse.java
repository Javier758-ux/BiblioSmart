package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class DisponibilidadLibroResponse {
    private Long idLibro;
    private String titulo;
    private long totalEjemplares;
    private long ejemplaresDisponibles;
    private boolean disponible;
}