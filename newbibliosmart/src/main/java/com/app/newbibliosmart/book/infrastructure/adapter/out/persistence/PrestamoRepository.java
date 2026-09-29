package com.app.newbibliosmart.book.infrastructure.adapter.out.persistence;

import com.app.newbibliosmart.book.domain.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByLector_IdLector(Long idLector);
    List<Prestamo> findByEjemplar_IdEjemplar(Long idEjemplar);
    List<Prestamo> findByEstado(String estado);
    boolean existsByEjemplar_IdEjemplarAndEstado(Long idEjemplar, String estado);
    long countByLector_IdLectorAndEstado(Long idLector, String estado);
    List<Prestamo> findByEstadoAndFechaVencimientoBefore(
            String estado,
            LocalDate fecha
    );
    long countByEstado(String estado);

    long countByEstadoAndFechaVencimientoBefore(
            String estado,
            LocalDate fecha
    );
}