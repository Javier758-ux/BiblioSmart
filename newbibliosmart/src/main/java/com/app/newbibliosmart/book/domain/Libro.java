package com.app.newbibliosmart.book.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name="libro")
@Getter
@Setter
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Long idLibro;

    private String titulo;
    @Column(length = 20, unique = true)
    private String isbn;
    private String categoria;

    @Column(name = "anio_publicacion")
    private Integer anioPublicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_editorial", nullable = false)
    private Editorial editorial;

    public Libro(String titulo, String isbn, String categoria,
            Integer anioPublicacion,
            Editorial editorial) {

        this.titulo = titulo;
        this.isbn = isbn;
        this.categoria = categoria;
        this.anioPublicacion = anioPublicacion;
        this.editorial = editorial;
    }
}