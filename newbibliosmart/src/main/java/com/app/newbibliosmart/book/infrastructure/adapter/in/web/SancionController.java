package com.app.newbibliosmart.book.infrastructure.adapter.in.web;

import com.app.newbibliosmart.book.application.SancionService;
import com.app.newbibliosmart.book.domain.Sancion;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.CrearSancionRequest;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.SancionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/sanciones") @RequiredArgsConstructor
public class SancionController {
    private final SancionService sancionService;

    @PostMapping
    public ResponseEntity<SancionResponse> registrar(
            @Valid @RequestBody CrearSancionRequest request) {
        Sancion sancion = sancionService.registrar(
                request.getIdLector(),
                request.getFechaFinal(),
                request.getMotivo()
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(convertirAResponse(sancion));
    }

    @GetMapping
    public List<SancionResponse> listar() {
        return sancionService.listar()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public SancionResponse buscarPorId(@PathVariable Long id) {
        Sancion sancion = sancionService.buscarPorId(id);
        return convertirAResponse(sancion);
    }

    @GetMapping("/lector/{idLector}")
    public List<SancionResponse> listarPorLector(@PathVariable Long idLector) {
        return sancionService.listarPorLector(idLector)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    private SancionResponse convertirAResponse(Sancion sancion) {
        return new SancionResponse(
                sancion.getIdSancion(),
                sancion.getLector().getIdLector(),
                sancion.getLector().getNroLector(),
                sancion.getFechaInicio(),
                sancion.getFechaFinal(),
                sancion.getMotivo(),
                sancion.getEstado()
        );
    }
}