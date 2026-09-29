package com.app.newbibliosmart.book.infrastructure.adapter.out.persistence;

import com.app.newbibliosmart.book.domain.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {
    Optional<Ejemplar> findByCodigo(String codigo);
    long countByLibro_IdLibro(Long idLibro);
    long countByLibro_IdLibroAndEstado(Long idLibro, String estado);
    long countByEstado(String estado);
}