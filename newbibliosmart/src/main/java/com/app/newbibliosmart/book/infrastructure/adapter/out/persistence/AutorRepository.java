package com.app.newbibliosmart.book.infrastructure.adapter.out.persistence;

import com.app.newbibliosmart.book.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombre(String nombre);
}