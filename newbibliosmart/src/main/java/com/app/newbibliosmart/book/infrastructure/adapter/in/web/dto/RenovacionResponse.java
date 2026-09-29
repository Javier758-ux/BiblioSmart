package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter @AllArgsConstructor
public class RenovacionResponse {
    private Long id;
    private Long idPrestamo;
    private LocalDate fechaRenovacion;
    private LocalDate nuevaFechaVencimiento;
}