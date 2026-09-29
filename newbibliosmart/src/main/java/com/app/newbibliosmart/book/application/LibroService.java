package com.app.newbibliosmart.book.application;

import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.LibroAutorRepository;
import com.app.newbibliosmart.book.domain.Editorial;
import com.app.newbibliosmart.book.domain.Libro;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.EditorialRepository;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.LibroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor

public class LibroService {

    private final LibroRepository libroRepository;
    private final EditorialRepository editorialRepository;
    private final LibroAutorRepository libroAutorRepository;

    @Transactional
    public Libro registrar(String titulo, String isbn, String categoria, Integer anioPublicacion, String nombreEditorial) {
        Editorial editorial = editorialRepository.findByNombre(nombreEditorial)
                .orElseGet(() ->
                        editorialRepository.save(new Editorial(nombreEditorial)));

        Libro libro = new Libro();

        libro.setTitulo(titulo);
        libro.setIsbn(isbn);
        libro.setCategoria(categoria);
        libro.setAnioPublicacion(anioPublicacion);
        libro.setEditorial(editorial);

        return libroRepository.save(libro);
    }

    @Transactional(readOnly = true)
    public List<Libro> listar() {
        return libroRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Libro buscarPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Libro no encontrado con ID: " + id
                        ));
    }
    @Transactional(readOnly = true)
    public List<Libro> buscarCatalogo(String texto) {
        List<Libro> resultados = new ArrayList<>();
        agregarSinDuplicados(
                resultados,
                libroRepository.findByTituloContainingIgnoreCase(texto)
        );
        agregarSinDuplicados(
                resultados,
                libroRepository.findByCategoriaContainingIgnoreCase(texto)
        );
        libroRepository.findByIsbn(texto)
                .ifPresent(libro ->
                        agregarSinDuplicado(resultados, libro)
                );
        libroAutorRepository
                .findByAutor_NombreContainingIgnoreCase(texto)
                .forEach(libroAutor ->
                        agregarSinDuplicado(
                                resultados,
                                libroAutor.getLibro()
                        )
                );
        return resultados;
    }
    private void agregarSinDuplicados(
            List<Libro> resultados,
            List<Libro> libros) {
        for (Libro libro : libros) {
            agregarSinDuplicado(resultados, libro);
        }
    }
    private void agregarSinDuplicado(
            List<Libro> resultados,
            Libro libro) {
        boolean existe = false;
        for (Libro resultado : resultados) {
            if (resultado.getIdLibro().equals(libro.getIdLibro())) {
                existe = true;
                break;
            }
        }
        if (!existe) {
            resultados.add(libro);
        }
    }
}