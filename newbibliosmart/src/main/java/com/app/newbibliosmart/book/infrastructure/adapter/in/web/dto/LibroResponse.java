package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
@Getter
@AllArgsConstructor

public class LibroResponse {
    private Long id;
    private String titulo;
    private String isbn;
    private String categoria;
    private List<String> autores;
    private Integer anioPublicacion;
    private String editorial;
}