package com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class CrearSancionRequest {
    @NotNull
    private Long idLector;
    private LocalDate fechaFinal;

    @NotBlank
    private String motivo;
}