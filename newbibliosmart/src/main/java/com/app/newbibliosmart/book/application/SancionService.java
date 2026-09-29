package com.app.newbibliosmart.book.application;

import com.app.newbibliosmart.book.domain.Lector;
import com.app.newbibliosmart.book.domain.Sancion;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.LectorRepository;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.SancionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service @RequiredArgsConstructor
public class SancionService {

    private final SancionRepository sancionRepository;
    private final LectorRepository lectorRepository;

    @Transactional
    public Sancion registrar(
            Long idLector,
            LocalDate fechaFinal,
            String motivo) {
        Lector lector = lectorRepository.findById(idLector).orElseThrow(() ->
                        new IllegalArgumentException("Lector no encontrado con ID: " + idLector));
        Sancion sancion = new Sancion();
        sancion.setLector(lector);
        sancion.setFechaInicio(LocalDate.now());
        sancion.setFechaFinal(fechaFinal);
        sancion.setMotivo(motivo);
        sancion.setEstado("ACTIVA");

        return sancionRepository.save(sancion);
    }

    @Transactional(readOnly = true)
    public List<Sancion> listar() {
        return sancionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Sancion buscarPorId(Long id) {
        return sancionRepository.findById(id).orElseThrow(() ->
                        new IllegalArgumentException("Sanción no encontrada con ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<Sancion> listarPorLector(Long idLector) {
        if (!lectorRepository.existsById(idLector)) {
            throw new IllegalArgumentException("Lector no encontrado con ID: " + idLector);
        }

        return sancionRepository.findByLector_IdLector(idLector);
    }
}