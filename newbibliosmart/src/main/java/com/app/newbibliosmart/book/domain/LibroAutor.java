package com.app.newbibliosmart.book.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "libro_autor", uniqueConstraints = { @UniqueConstraint(columnNames = {"id_libro", "id_autor"})})
@Getter @Setter @NoArgsConstructor
public class LibroAutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro_autor")
    private Long idLibroAutor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_libro", nullable = false)
    private Libro libro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor autor;

    public LibroAutor(Libro libro, Autor autor) {
        this.libro = libro;
        this.autor = autor;
    }
}
