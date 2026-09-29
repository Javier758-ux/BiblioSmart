package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class DashboardResponse {

    private long prestamosActivos;
    private long prestamosEnMora;
    private long ejemplaresDisponibles;
    private long ejemplaresPrestados;
    private long reservasActivas;
}