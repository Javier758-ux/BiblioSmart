package com.app.newbibliosmart.book.infrastructure.adapter.out.persistence;
import com.app.newbibliosmart.book.domain.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
    Optional<Libro> findByIsbn(String isbn);
    List<Libro> findByCategoriaContainingIgnoreCase(String categoria);
}
