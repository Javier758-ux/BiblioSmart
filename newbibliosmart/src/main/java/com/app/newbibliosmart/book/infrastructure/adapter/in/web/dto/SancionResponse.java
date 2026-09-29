package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter @AllArgsConstructor
public class SancionResponse {
    private Long id;
    private Long idLector;
    private String nroLector;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private String motivo;
    private String estado;
}