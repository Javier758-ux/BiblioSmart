package com.app.newbibliosmart.book.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "sancion") @Getter @Setter @NoArgsConstructor
public class Sancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sancion")
    private Long idSancion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lector", nullable = false)
    private Lector lector;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_final")
    private LocalDate fechaFinal;

    @Column(name = "motivo", nullable = false, length = 250)
    private String motivo;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    public Sancion(Lector lector, LocalDate fechaInicio, LocalDate fechaFinal, String motivo, String estado) {
        this.lector = lector;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.motivo = motivo;
        this.estado = estado;
    }
}