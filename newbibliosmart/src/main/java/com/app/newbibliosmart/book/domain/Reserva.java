package com.app.newbibliosmart.book.domain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "reserva")
@Getter @Setter @NoArgsConstructor

public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long idReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lector", nullable = false)
    private Lector lector;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_libro", nullable = false)
    private Libro libro;

    @Column(name = "fecha_reserva", nullable = false)
    private LocalDate fechaReserva;

    @Column(name = "fecha_limite_retiro", nullable = false)
    private LocalDate fechaLimiteRetiro;

    @Column(name = "estado", nullable = false, length = 30)
    private String estado;

    public Reserva(
            Lector lector,
            Libro libro,
            LocalDate fechaReserva,
            LocalDate fechaLimiteRetiro,
            String estado) {

        this.lector = lector;
        this.libro = libro;
        this.fechaReserva = fechaReserva;
        this.fechaLimiteRetiro = fechaLimiteRetiro;
        this.estado = estado;
    }
}
