package com.app.newbibliosmart.book.application;

import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.DashboardResponse;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.EjemplarRepository;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.PrestamoRepository;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service @RequiredArgsConstructor
public class DashboardService {

    private final PrestamoRepository prestamoRepository;
    private final EjemplarRepository ejemplarRepository;
    private final ReservaRepository reservaRepository;

    @Transactional(readOnly = true)
    public DashboardResponse obtenerResumen() {

        long prestamosActivos =
                prestamoRepository.countByEstado("ACTIVO");

        long prestamosEnMora =
                prestamoRepository.countByEstadoAndFechaVencimientoBefore(
                        "ACTIVO",
                        LocalDate.now()
                );

        long ejemplaresDisponibles =
                ejemplarRepository.countByEstado("DISPONIBLE");

        long ejemplaresPrestados =
                ejemplarRepository.countByEstado("PRESTADO");

        long reservasActivas =
                reservaRepository.countByEstado("ACTIVA");

        return new DashboardResponse(
                prestamosActivos,
                prestamosEnMora,
                ejemplaresDisponibles,
                ejemplaresPrestados,
                reservasActivas
        );
    }
}