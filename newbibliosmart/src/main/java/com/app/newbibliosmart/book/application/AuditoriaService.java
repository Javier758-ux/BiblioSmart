package com.app.newbibliosmart.book.application;

import com.app.newbibliosmart.book.domain.Auditoria;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.AuditoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service @RequiredArgsConstructor
public class AuditoriaService {
    private final AuditoriaRepository auditoriaRepository;

    @Transactional
    public Auditoria registrar(
            String entidadAfectada,
            Long idRegistro,
            String accion,
            String usuarioResponsable) {

        Auditoria auditoria = new Auditoria();

        auditoria.setEntidadAfectada(entidadAfectada);
        auditoria.setIdRegistro(idRegistro);
        auditoria.setAccion(accion);
        auditoria.setFechaHora(LocalDateTime.now());
        auditoria.setUsuarioResponsable(usuarioResponsable);

        return auditoriaRepository.save(auditoria);
    }

    @Transactional(readOnly = true)
    public List<Auditoria> listar() {

        return auditoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Auditoria buscarPorId(Long id) {

        return auditoriaRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Auditoría no encontrada con ID: " + id
                        )
                );
    }

    @Transactional(readOnly = true)
    public List<Auditoria> listarPorEntidad(String entidadAfectada) {

        return auditoriaRepository
                .findByEntidadAfectada(entidadAfectada);
    }

    @Transactional(readOnly = true)
    public List<Auditoria> listarPorUsuario(String usuarioResponsable) {

        return auditoriaRepository
                .findByUsuarioResponsable(usuarioResponsable);
    }
}