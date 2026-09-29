package com.app.newbibliosmart.book.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "editorial")
@Getter @Setter @NoArgsConstructor
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_editorial")
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @OneToMany(
            mappedBy = "editorial",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Libro> libros = new ArrayList<>();

    public Editorial(String nombre) {
        this.nombre = nombre;
    }

    public void addLibro(Libro libro) {
        libros.add(libro);
        libro.setEditorial(this);
    }

    public void removeLibro(Libro libro) {
        libros.remove(libro);
        libro.setEditorial(null);
    }
}