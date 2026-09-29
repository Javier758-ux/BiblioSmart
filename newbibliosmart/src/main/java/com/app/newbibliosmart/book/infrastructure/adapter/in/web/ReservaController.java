package com.app.newbibliosmart.book.infrastructure.adapter.in.web;

import com.app.newbibliosmart.book.application.ReservaService;
import com.app.newbibliosmart.book.domain.Reserva;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.CrearReservaRequest;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.ReservaResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor

public class ReservaController {

    private final ReservaService reservaService;

    // 1. GET solo para listar
    @GetMapping
    public List<ReservaResponse> listar() {
        return reservaService.listar().stream()
                .map(reserva -> new ReservaResponse(
                        reserva.getIdReserva(),
                        reserva.getLibro().getTitulo(),
                        reserva.getLector().getNroLector(),
                        reserva.getFechaReserva(),
                        reserva.getFechaLimiteRetiro(),
                        reserva.getEstado()
                ))
                .toList();
    }

    // 2. POST solo para registrar
    @PostMapping
    public ResponseEntity<ReservaResponse> registrar(@Valid @RequestBody CrearReservaRequest request) {
        Reserva reserva = reservaService.registrar(request.getIdLector(), request.getIdLibro());

        ReservaResponse response = new ReservaResponse(
                reserva.getIdReserva(),
                reserva.getLibro().getTitulo(),
                reserva.getLector().getNroLector(),
                reserva.getFechaReserva(),
                reserva.getFechaLimiteRetiro(),
                reserva.getEstado()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/liberar-vencidas")
    public List<ReservaResponse> liberarReservasVencidas() {

        return reservaService.liberarReservasVencidas()
                .stream()
                .map(reserva -> new ReservaResponse(
                        reserva.getIdReserva(),
                        reserva.getLibro().getTitulo(),
                        reserva.getLector().getNroLector(),
                        reserva.getFechaReserva(),
                        reserva.getFechaLimiteRetiro(),
                        reserva.getEstado()
                ))
                .toList();
    }
    @DeleteMapping("/{id}")
    public ReservaResponse cancelar(@PathVariable Long id) {

        Reserva reserva = reservaService.cancelar(id);

        return new ReservaResponse(
                reserva.getIdReserva(),
                reserva.getLibro().getTitulo(),
                reserva.getLector().getNroLector(),
                reserva.getFechaReserva(),
                reserva.getFechaLimiteRetiro(),
                reserva.getEstado()
        );
    }
}