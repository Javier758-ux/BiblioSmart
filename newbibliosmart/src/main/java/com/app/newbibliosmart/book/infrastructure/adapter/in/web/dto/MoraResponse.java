package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MoraResponse {

    private Long idPrestamo;
    private Long idLector;
    private String nroLector;

    private Long idEjemplar;
    private String codigoEjemplar;

    private String tituloLibro;

    private LocalDate fechaVencimiento;

    private long diasMora;
}