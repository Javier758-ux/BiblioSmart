package com.app.newbibliosmart.book.application;

import com.app.newbibliosmart.book.domain.Autor;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;

    @Transactional
    public Autor registrar(String nombre) {
        Autor autor = new Autor(nombre);

        return autorRepository.save(autor);
    }

    @Transactional(readOnly = true)
    public List<Autor> listar() {
        return autorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Autor buscarPorId(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Autor no encontrado con ID: " + id
                        ));
    }
}