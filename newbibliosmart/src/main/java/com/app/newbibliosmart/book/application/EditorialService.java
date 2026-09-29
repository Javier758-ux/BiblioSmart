package com.app.newbibliosmart.book.application;

import com.app.newbibliosmart.book.domain.Editorial;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.EditorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
public class EditorialService {
    private final EditorialRepository editorialRepository;
    @Transactional
    public Editorial registrar(String nombre) {
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        return editorialRepository.save(editorial);
    }

    @Transactional(readOnly = true)
    public List<Editorial> listar() {
        return editorialRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Editorial buscarPorId(Long id) {
        return editorialRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Editorial no encontrada con ID: " + id
                        )
                );
    }
}