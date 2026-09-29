package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class UsuarioResponse {
    private Long id;
    private Long idRol;
    private String nombreRol;
    private String nombreCompleto;
    private String estado;
}