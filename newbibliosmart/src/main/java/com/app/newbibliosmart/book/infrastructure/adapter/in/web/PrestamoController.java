package com.app.newbibliosmart.book.infrastructure.adapter.in.web;

import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.MoraResponse;
import com.app.newbibliosmart.book.application.RenovacionService;
import com.app.newbibliosmart.book.domain.Renovacion;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.RenovacionResponse;
import com.app.newbibliosmart.book.application.PrestamoService;
import com.app.newbibliosmart.book.domain.Prestamo;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.CrearPrestamoRequest;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.PrestamoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
@RequiredArgsConstructor
public class PrestamoController {

    private final PrestamoService prestamoService;
    private final RenovacionService renovacionService;
    @PostMapping
    public ResponseEntity<PrestamoResponse> registrar(@Valid @RequestBody CrearPrestamoRequest request) {
        Prestamo prestamo = prestamoService.registrar(request.getIdLector(), request.getIdEjemplar());
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirAResponse(prestamo));
    }

    @GetMapping
    public List<PrestamoResponse> listar() {
        return prestamoService.listar().stream().map(this::convertirAResponse).toList();
    }
    @GetMapping("/mora")
    public List<MoraResponse> listarEnMora() {

        return prestamoService.listarEnMora()
                .stream()
                .map(prestamo -> new MoraResponse(
                        prestamo.getIdPrestamo(),
                        prestamo.getLector().getIdLector(),
                        prestamo.getLector().getNroLector(),
                        prestamo.getEjemplar().getIdEjemplar(),
                        prestamo.getEjemplar().getCodigo(),
                        prestamo.getEjemplar().getLibro().getTitulo(),
                        prestamo.getFechaVencimiento(),
                        prestamoService.calcularDiasMora(prestamo)
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public PrestamoResponse buscarPorId(@PathVariable Long id) {

        Prestamo prestamo = prestamoService.buscarPorId(id);

        return convertirAResponse(prestamo);
    }
    @PostMapping("/{id}/devolver")
    public PrestamoResponse devolver(@PathVariable Long id) {

        Prestamo prestamo = prestamoService.devolver(id);

        return convertirAResponse(prestamo);
    }
    @PostMapping("/{id}/renovar")
    public RenovacionResponse renovar(@PathVariable Long id) {
        Renovacion renovacion = renovacionService.renovar(id);
        return convertirRenovacionAResponse(renovacion);
    }

    @GetMapping("/{id}/renovaciones")
    public List<RenovacionResponse> listarRenovaciones(@PathVariable Long id) {
        return renovacionService.listarPorPrestamo(id)
                .stream()
                .map(this::convertirRenovacionAResponse)
                .toList();
    }
    @GetMapping("/lector/{idLector}")
    public List<PrestamoResponse> listarPorLector(
            @PathVariable Long idLector) {

        return prestamoService.listarPorLector(idLector)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @GetMapping("/ejemplar/{idEjemplar}")
    public List<PrestamoResponse> listarPorEjemplar(
            @PathVariable Long idEjemplar) {

        return prestamoService.listarPorEjemplar(idEjemplar)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }
    private PrestamoResponse convertirAResponse(Prestamo prestamo) {

        return new PrestamoResponse(
                prestamo.getIdPrestamo(),
                prestamo.getLector().getIdLector(),
                prestamo.getLector().getNroLector(),
                prestamo.getEjemplar().getIdEjemplar(),
                prestamo.getEjemplar().getCodigo(),
                prestamo.getEjemplar().getLibro().getIdLibro(),
                prestamo.getEjemplar().getLibro().getTitulo(),
                prestamo.getFechaPrestamo(),
                prestamo.getFechaVencimiento(),
                prestamo.getFechaDevolucion(),
                prestamo.getEstado()
        );
    }
    private RenovacionResponse convertirRenovacionAResponse(Renovacion renovacion) {

        return new RenovacionResponse(
                renovacion.getIdRenovacion(),
                renovacion.getPrestamo().getIdPrestamo(),
                renovacion.getFechaRenovacion(),
                renovacion.getNuevaFechaVencimiento()
        );
    }
}