package com.app.newbibliosmart.book.infrastructure.adapter.out.persistence;

import com.app.newbibliosmart.book.domain.LibroAutor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LibroAutorRepository extends JpaRepository<LibroAutor, Long> {
    List<LibroAutor> findByLibro_IdLibro(Long idLibro);
    List<LibroAutor> findByAutor_NombreContainingIgnoreCase(String nombre);
}