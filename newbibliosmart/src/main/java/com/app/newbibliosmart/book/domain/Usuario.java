package com.app.newbibliosmart.book.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter @Setter @NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @Column(name = "nombre_completo", nullable = false, length = 160)
    private String nombreCompleto;

    @Column(name = "credenciales", nullable = false, length = 255)
    private String credenciales;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    public Usuario(Rol rol, String nombreCompleto, String credenciales, String estado) {
        this.rol = rol;
        this.nombreCompleto = nombreCompleto;
        this.credenciales = credenciales;
        this.estado = estado;
    }
}