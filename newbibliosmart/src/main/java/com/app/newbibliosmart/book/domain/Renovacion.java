package com.app.newbibliosmart.book.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "renovacion")
@Getter @Setter @NoArgsConstructor
public class Renovacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_renovacion")
    private Long idRenovacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prestamo", nullable = false)
    private Prestamo prestamo;

    @Column(name = "fecha_renovacion", nullable = false)
    private LocalDate fechaRenovacion;
    @Column(name = "nueva_fecha_vencimiento", nullable = false)
    private LocalDate nuevaFechaVencimiento;
    public Renovacion(Prestamo prestamo, LocalDate fechaRenovacion, LocalDate nuevaFechaVencimiento) {
        this.prestamo = prestamo;
        this.fechaRenovacion = fechaRenovacion;
        this.nuevaFechaVencimiento = nuevaFechaVencimiento;
    }
}