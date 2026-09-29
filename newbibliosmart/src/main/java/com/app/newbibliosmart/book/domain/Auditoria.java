package com.app.newbibliosmart.book.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "auditoria")
@Getter @Setter @NoArgsConstructor
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria")
    private Long idAuditoria;

    @Column(name = "entidad_afectada", nullable = false, length = 100)
    private String entidadAfectada;

    @Column(name = "id_registro", nullable = false)
    private Long idRegistro;

    @Column(name = "accion", nullable = false, length = 50)
    private String accion;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "usuario_responsable", nullable = false, length = 160)
    private String usuarioResponsable;

    public Auditoria(String entidadAfectada, Long idRegistro, String accion, LocalDateTime fechaHora,
                     String usuarioResponsable) {
        this.entidadAfectada = entidadAfectada;
        this.idRegistro = idRegistro;
        this.accion = accion;
        this.fechaHora = fechaHora;
        this.usuarioResponsable = usuarioResponsable;
    }
}