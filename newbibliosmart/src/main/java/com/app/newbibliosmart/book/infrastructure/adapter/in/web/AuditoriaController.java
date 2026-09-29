package com.app.newbibliosmart.book.infrastructure.adapter.in.web;

import com.app.newbibliosmart.book.application.AuditoriaService;
import com.app.newbibliosmart.book.domain.Auditoria;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.AuditoriaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/auditorias") @RequiredArgsConstructor
public class AuditoriaController {
    private final AuditoriaService auditoriaService;

    @GetMapping
    public List<AuditoriaResponse> listar() {
        return auditoriaService.listar()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public AuditoriaResponse buscarPorId(@PathVariable Long id) {
        Auditoria auditoria = auditoriaService.buscarPorId(id);
        return convertirAResponse(auditoria);
    }

    @GetMapping("/entidad/{entidadAfectada}")
    public List<AuditoriaResponse> listarPorEntidad(@PathVariable String entidadAfectada) {
        return auditoriaService.listarPorEntidad(entidadAfectada)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @GetMapping("/usuario/{usuarioResponsable}")
    public List<AuditoriaResponse> listarPorUsuario(@PathVariable String usuarioResponsable) {
        return auditoriaService.listarPorUsuario(usuarioResponsable)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    private AuditoriaResponse convertirAResponse(Auditoria auditoria) {
        return new AuditoriaResponse(
                auditoria.getIdAuditoria(),
                auditoria.getEntidadAfectada(),
                auditoria.getIdRegistro(),
                auditoria.getAccion(),
                auditoria.getFechaHora(),
                auditoria.getUsuarioResponsable()
        );
    }
}