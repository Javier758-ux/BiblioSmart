package com.app.newbibliosmart.book.application;

import com.app.newbibliosmart.book.domain.Ejemplar;
import com.app.newbibliosmart.book.domain.Libro;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.EjemplarRepository;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.LibroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EjemplarService {

    private final EjemplarRepository ejemplarRepository;
    private final LibroRepository libroRepository;

    @Transactional
    public Ejemplar registrar(
            String codigo,
            Long idLibro) {

        Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Libro no encontrado con ID: " + idLibro
                        ));

        Ejemplar ejemplar = new Ejemplar();

        ejemplar.setCodigo(codigo);
        ejemplar.setEstado("DISPONIBLE");
        ejemplar.setLibro(libro);

        return ejemplarRepository.save(ejemplar);
    }

    @Transactional(readOnly = true)
    public List<Ejemplar> listar() {
        return ejemplarRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Ejemplar buscarPorId(Long id) {
        return ejemplarRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Ejemplar no encontrado con ID: " + id
                        ));
    }
    @Transactional(readOnly = true)
    public long contarEjemplaresPorLibro(Long idLibro) {
        if (!libroRepository.existsById(idLibro)) {
            throw new IllegalArgumentException(
                    "Libro no encontrado con ID: " + idLibro
            );
        }
        return ejemplarRepository.countByLibro_IdLibro(idLibro);
    }

    @Transactional(readOnly = true)
    public long contarEjemplaresDisponibles(Long idLibro) {
        if (!libroRepository.existsById(idLibro)) {
            throw new IllegalArgumentException(
                    "Libro no encontrado con ID: " + idLibro
            );
        }
        return ejemplarRepository
                .countByLibro_IdLibroAndEstado(idLibro, "DISPONIBLE");
    }
}