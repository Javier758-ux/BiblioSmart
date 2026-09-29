package com.app.newbibliosmart.book.application;

import com.app.newbibliosmart.book.domain.Reserva;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.ReservaRepository;
import java.time.temporal.ChronoUnit;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.SancionRepository;
import com.app.newbibliosmart.book.domain.Ejemplar;
import com.app.newbibliosmart.book.domain.Lector;
import com.app.newbibliosmart.book.domain.Prestamo;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.EjemplarRepository;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.LectorRepository;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.PrestamoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrestamoService {
    private final PrestamoRepository prestamoRepository;
    private final LectorRepository lectorRepository;
    private final EjemplarRepository ejemplarRepository;
    private final SancionRepository sancionRepository;
    private final AuditoriaService auditoriaService;
    private final ReservaRepository reservaRepository;

    @Value("${bibliosmart.prestamo.plazo-dias}")
    private long plazoPrestamoDias;
    @Value("${bibliosmart.prestamo.maximos-por-lector}")
    private long maximosPrestamosPorLector;
    @Transactional
    public Prestamo registrar(Long idLector, Long idEjemplar) {

        Lector lector = lectorRepository.findById(idLector)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Lector no encontrado con ID: " + idLector
                        )
                );
        boolean tieneSancionActiva =
                sancionRepository.existsByLector_IdLectorAndEstado(
                        idLector,
                        "ACTIVA"
                );

        if (tieneSancionActiva) {
            throw new IllegalArgumentException(
                    "El lector tiene una sanción activa y no puede realizar préstamos"
            );
        }
        long prestamosActivos =
                prestamoRepository
                        .countByLector_IdLectorAndEstado(
                                idLector,
                                "ACTIVO"
                        );
        if (prestamosActivos >= maximosPrestamosPorLector) {
            throw new IllegalArgumentException(
                    "El lector alcanzó el máximo de préstamos simultáneos"
            );
        }
        Ejemplar ejemplar = ejemplarRepository.findById(idEjemplar)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Ejemplar no encontrado con ID: " + idEjemplar
                        )
                );
        Reserva reservaPriorizada = null;

        if ("RESERVADO".equals(ejemplar.getEstado())) {

            Long idLibro = ejemplar.getLibro().getIdLibro();

            reservaPriorizada = reservaRepository
                    .findFirstByLibro_IdLibroAndLector_IdLectorAndEstado(
                            idLibro,
                            idLector,
                            "PRIORIZADA"
                    )
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "El ejemplar está reservado para otro lector"
                            )
                    );

        } else if (!"DISPONIBLE".equals(ejemplar.getEstado())) {

            throw new IllegalArgumentException(
                    "El ejemplar no está disponible para préstamo"
            );
        }
        if (prestamoRepository
                .existsByEjemplar_IdEjemplarAndEstado(idEjemplar, "ACTIVO")) {

            throw new IllegalArgumentException(
                    "El ejemplar ya tiene un préstamo activo"
            );
        }
        LocalDate fechaPrestamo = LocalDate.now();
        Prestamo prestamo = new Prestamo();

        prestamo.setLector(lector);
        prestamo.setEjemplar(ejemplar);
        prestamo.setFechaPrestamo(fechaPrestamo);
        prestamo.setFechaVencimiento(
                fechaPrestamo.plusDays(plazoPrestamoDias)
        );
        prestamo.setEstado("ACTIVO");
        ejemplar.setEstado("PRESTADO");
        ejemplarRepository.save(ejemplar);
        Prestamo prestamoGuardado =
                prestamoRepository.save(prestamo);
        if (reservaPriorizada != null) {
            reservaPriorizada.setEstado("ATENDIDA");
            reservaRepository.save(reservaPriorizada);
        }
        auditoriaService.registrar(
                "PRESTAMO",
                prestamoGuardado.getIdPrestamo(),
                "CREAR",
                "SISTEMA"
        );
        return prestamoGuardado;
    }
    @Transactional(readOnly = true)
    public List<Prestamo> listar() {
        return prestamoRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Prestamo buscarPorId(Long id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Préstamo no encontrado con ID: " + id
                        )
                );
    }

    @Transactional(readOnly = true)
    public List<Prestamo> listarPorLector(Long idLector) {
        if (!lectorRepository.existsById(idLector)) {
            throw new IllegalArgumentException(
                    "Lector no encontrado con ID: " + idLector);
        }
        return prestamoRepository.findByLector_IdLector(idLector);
    }

    @Transactional(readOnly = true)
    public List<Prestamo> listarPorEjemplar(Long idEjemplar) {
        if (!ejemplarRepository.existsById(idEjemplar)) {
            throw new IllegalArgumentException(
                    "Ejemplar no encontrado con ID: " + idEjemplar
            );
        }
        return prestamoRepository.findByEjemplar_IdEjemplar(idEjemplar);
    }
    @Transactional(readOnly = true)
    public List<Prestamo> listarEnMora() {
        return prestamoRepository.findByEstadoAndFechaVencimientoBefore(
                "ACTIVO",
                LocalDate.now()
        );
    }

    public long calcularDiasMora(Prestamo prestamo) {

        LocalDate fechaFinal;

        if (prestamo.getFechaDevolucion() != null) {
            fechaFinal = prestamo.getFechaDevolucion();
        } else {
            fechaFinal = LocalDate.now();
        }

        if (!fechaFinal.isAfter(prestamo.getFechaVencimiento())) {
            return 0;
        }

        return ChronoUnit.DAYS.between(
                prestamo.getFechaVencimiento(),
                fechaFinal
        );
    }

    @Transactional
    public Prestamo devolver(Long idPrestamo) {

        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Préstamo no encontrado con ID: " + idPrestamo
                        )
                );

        if ("DEVUELTO".equals(prestamo.getEstado())) {
            throw new IllegalArgumentException(
                    "El préstamo ya fue devuelto"
            );
        }

        prestamo.setFechaDevolucion(LocalDate.now());
        prestamo.setEstado("DEVUELTO");

        Ejemplar ejemplar = prestamo.getEjemplar();

        Long idLibro = ejemplar.getLibro().getIdLibro();

        Reserva reservaPrioritaria =
                reservaRepository
                        .findFirstByLibro_IdLibroAndEstadoAndFechaLimiteRetiroGreaterThanEqualOrderByFechaReservaAsc(
                                idLibro,
                                "ACTIVA",
                                LocalDate.now()
                        )
                        .orElse(null);

        if (reservaPrioritaria != null) {

            ejemplar.setEstado("RESERVADO");

            reservaPrioritaria.setEstado("PRIORIZADA");
            reservaRepository.save(reservaPrioritaria);

        } else {

            ejemplar.setEstado("DISPONIBLE");
        }
        ejemplarRepository.save(ejemplar);
        return prestamoRepository.save(prestamo);
    }
}