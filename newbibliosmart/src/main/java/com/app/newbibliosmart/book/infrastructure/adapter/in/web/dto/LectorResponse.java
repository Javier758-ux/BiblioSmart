package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@Getter @AllArgsConstructor
public class LectorResponse {
    private Long id;
    private String nroLector;
    private String datosContacto;
    private LocalDate fechaRegistro;
}
