package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EjemplarResponse {
    private Long id;
    private String codigo;
    private String estado;
    private Long idLibro;
    private String tituloLibro;
}