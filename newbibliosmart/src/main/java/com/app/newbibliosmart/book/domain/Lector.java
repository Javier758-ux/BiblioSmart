package com.app.newbibliosmart.book.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "lector")
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lector")
    private Long idLector;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "nro_lector", nullable = false, length = 50)
    private String nroLector;

    @Column(name = "datos_contacto", nullable = false)
    private String datosContacto;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    public Lector(
            Usuario usuario,
            String nroLector,
            String datosContacto,
            LocalDate fechaRegistro) {

        this.usuario = usuario;
        this.nroLector = nroLector;
        this.datosContacto = datosContacto;
        this.fechaRegistro = fechaRegistro;
    }
}
