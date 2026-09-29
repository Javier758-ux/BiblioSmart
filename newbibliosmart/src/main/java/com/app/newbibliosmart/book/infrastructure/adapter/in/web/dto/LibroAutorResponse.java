package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter @AllArgsConstructor
public class LibroAutorResponse {
    private Long id;
    private Long idLibro;
    private String tituloLibro;
    private Long idAutor;
    private String nombreAutor;
}
