package com.app.newbibliosmart.book.application;

import com.app.newbibliosmart.book.domain.Prestamo;
import com.app.newbibliosmart.book.domain.Renovacion;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.PrestamoRepository;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.RenovacionRepository;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service @RequiredArgsConstructor
public class RenovacionService {

    private final RenovacionRepository renovacionRepository;
    private final PrestamoRepository prestamoRepository;
    private final ReservaRepository reservaRepository;

    @Value("${bibliosmart.prestamo.plazo-dias}")
    private long plazoPrestamoDias;

    @Transactional
    public Renovacion renovar(Long idPrestamo) {

        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Préstamo no encontrado con ID: " + idPrestamo
                        )
                );

        if (!"ACTIVO".equals(prestamo.getEstado())) {
            throw new IllegalArgumentException(
                    "Solo se puede renovar un préstamo activo"
            );
        }

        Long idLibro = prestamo
                .getEjemplar()
                .getLibro()
                .getIdLibro();

        Long idLector = prestamo
                .getLector()
                .getIdLector();

        boolean reservadoPorOtroLector =
                reservaRepository
                        .existsByLibro_IdLibroAndEstadoAndLector_IdLectorNot(
                                idLibro,
                                "ACTIVA",
                                idLector
                        );

        if (reservadoPorOtroLector) {
            throw new IllegalArgumentException("No se puede renovar porque el libro está reservado por otro lector");
        }

        LocalDate fechaRenovacion = LocalDate.now();

        LocalDate nuevaFechaVencimiento = prestamo.getFechaVencimiento().plusDays(plazoPrestamoDias);

        Renovacion renovacion = new Renovacion();
        renovacion.setPrestamo(prestamo);
        renovacion.setFechaRenovacion(fechaRenovacion);
        renovacion.setNuevaFechaVencimiento(nuevaFechaVencimiento);
        prestamo.setFechaVencimiento(nuevaFechaVencimiento);
        prestamoRepository.save(prestamo);
        return renovacionRepository.save(renovacion);
    }

    @Transactional(readOnly = true)
    public List<Renovacion> listarPorPrestamo(Long idPrestamo) {
        return renovacionRepository.findByPrestamo_IdPrestamo(idPrestamo);
    }
}