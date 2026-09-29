package com.app.newbibliosmart.book.infrastructure.adapter.out.persistence;

import com.app.newbibliosmart.book.domain.Renovacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RenovacionRepository extends JpaRepository<Renovacion, Long> {
    List<Renovacion> findByPrestamo_IdPrestamo(Long idPrestamo);
}