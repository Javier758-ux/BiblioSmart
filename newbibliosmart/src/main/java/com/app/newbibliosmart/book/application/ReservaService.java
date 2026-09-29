package com.app.newbibliosmart.book.application;

import org.springframework.beans.factory.annotation.Value;
import com.app.newbibliosmart.book.domain.Libro;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.LibroRepository;
import com.app.newbibliosmart.book.domain.Lector;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.LectorRepository;
import com.app.newbibliosmart.book.domain.Reserva;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final LibroRepository libroRepository;
    private final LectorRepository lectorRepository;
    @Value("${bibliosmart.reserva.plazo-retiro-dias}")
    private long plazoRetiroDias;


    @Transactional
    public Reserva registrar(Long idLector, Long idLibro) {

        Lector lector = lectorRepository.findById(idLector)
                .orElseThrow(() ->
                        new IllegalArgumentException("Lector no encontrado"));

        Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(() ->
                        new IllegalArgumentException("Libro no encontrado"));

        Reserva reserva = new Reserva();

        reserva.setLector(lector);
        reserva.setLibro(libro);
        reserva.setFechaReserva(LocalDate.now());
        reserva.setFechaLimiteRetiro(LocalDate.now().plusDays(plazoRetiroDias));
        reserva.setEstado("ACTIVA");

        return reservaRepository.save(reserva);
    }

    @Transactional(readOnly = true)
    public List<Reserva> listar() {
        return reservaRepository.findAll();
    }

    @Transactional
    public Reserva cancelar(Long idReserva) {

        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Reserva no encontrada con ID: " + idReserva
                        )
                );

        if (!"ACTIVA".equals(reserva.getEstado())) {
            throw new IllegalArgumentException(
                    "Solo se pueden cancelar reservas activas"
            );
        }

        reserva.setEstado("CANCELADA");

        return reservaRepository.save(reserva);
    }

    @Transactional
    public List<Reserva> liberarReservasVencidas() {

        List<Reserva> reservasVencidas =
                reservaRepository.findByEstadoAndFechaLimiteRetiroBefore(
                        "ACTIVA",
                        LocalDate.now()
                );

        for (Reserva reserva : reservasVencidas) {
            reserva.setEstado("VENCIDA");
        }

        return reservaRepository.saveAll(reservasVencidas);
    }
}
