package com.app.newbibliosmart.book.infrastructure.adapter.out.persistence;
import com.app.newbibliosmart.book.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    boolean existsByLibro_IdLibroAndEstadoAndLector_IdLectorNot(
            Long idLibro,
            String estado,
            Long idLector
    );
    List<Reserva> findByEstadoAndFechaLimiteRetiroBefore(
            String estado,
            LocalDate fecha
    );
    Optional<Reserva> findFirstByLibro_IdLibroAndLector_IdLectorAndEstado(
            Long idLibro,
            Long idLector,
            String estado
    );
    Optional<Reserva> findFirstByLibro_IdLibroAndEstadoAndFechaLimiteRetiroGreaterThanEqualOrderByFechaReservaAsc(
            Long idLibro,
            String estado,
            LocalDate fecha
    );
    long countByEstado(String estado);
}