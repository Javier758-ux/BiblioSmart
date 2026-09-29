package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter @AllArgsConstructor
public class PrestamoResponse {

    private Long id;
    private Long idLector;
    private String nroLector;
    private Long idEjemplar;
    private String codigoEjemplar;
    private Long idLibro;
    private String tituloLibro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaVencimiento;
    private LocalDate fechaDevolucion;
    private String estado;
}