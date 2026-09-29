package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
public class ReservaResponse {
    private Long id;
    private String tituloLibro;
    private String nroLector;
    private LocalDate fechaReserva;
    private LocalDate fechaLimiteRetiro;
    private String estado;
}
