package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @AllArgsConstructor
public class AuditoriaResponse {
    private Long id;
    private String entidadAfectada;
    private Long idRegistro;
    private String accion;
    private LocalDateTime fechaHora;
    private String usuarioResponsable;
}