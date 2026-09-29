package com.app.newbibliosmart.book.application;

import com.app.newbibliosmart.book.domain.Autor;
import com.app.newbibliosmart.book.domain.Libro;
import com.app.newbibliosmart.book.domain.LibroAutor;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.AutorRepository;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.LibroAutorRepository;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.LibroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroAutorService {

    private final LibroAutorRepository libroAutorRepository;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    @Transactional
    public LibroAutor registrar(Long idLibro, Long idAutor) {

        Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Libro no encontrado con ID: " + idLibro
                        ));

        Autor autor = autorRepository.findById(idAutor)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Autor no encontrado con ID: " + idAutor
                        ));

        LibroAutor libroAutor = new LibroAutor(libro, autor);

        return libroAutorRepository.save(libroAutor);
    }

    @Transactional(readOnly = true)
    public List<LibroAutor> listar() {
        return libroAutorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public LibroAutor buscarPorId(Long id) {
        return libroAutorRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Relación Libro-Autor no encontrada con ID: " + id
                        ));
    }
    @Transactional(readOnly = true)
    public List<String> obtenerAutoresPorLibro(Long idLibro) {
        return libroAutorRepository.findByLibro_IdLibro(idLibro)
                .stream()
                .map(libroAutor -> libroAutor.getAutor().getNombre())
                .toList();
    }
}