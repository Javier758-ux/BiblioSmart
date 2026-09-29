package com.app.newbibliosmart.book.infrastructure.adapter.in.web;

import com.app.newbibliosmart.book.application.LibroAutorService;
import com.app.newbibliosmart.book.application.LibroService;
import com.app.newbibliosmart.book.domain.Libro;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.CrearLibroRequest;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.LibroResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/libros")
@RequiredArgsConstructor

public class LibroController {

    private final LibroService libroService;
    private final LibroAutorService libroAutorService;

    @PostMapping
    public ResponseEntity<LibroResponse> registrar(
            @Valid @RequestBody CrearLibroRequest request) {

        Libro libro = libroService.registrar(request.getTitulo(), request.getIsbn(), request.getCategoria(),
                request.getAnioPublicacion(),
                request.getEditorial()
        );

        LibroResponse response = convertirAResponse(libro);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public List<LibroResponse> listar() {
        return libroService.listar()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public LibroResponse buscarPorId(@PathVariable Long id) {
        Libro libro = libroService.buscarPorId(id);
        return convertirAResponse(libro);
    }
    @GetMapping("/buscar")
    public List<LibroResponse> buscarCatalogo(
            @RequestParam String texto) {

        return libroService.buscarCatalogo(texto)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    private LibroResponse convertirAResponse(Libro libro) {
        return new LibroResponse(
                libro.getIdLibro(),
                libro.getTitulo(),
                libro.getIsbn(),
                libro.getCategoria(),
                libroAutorService.obtenerAutoresPorLibro(libro.getIdLibro()),
                libro.getAnioPublicacion(),
                libro.getEditorial().getNombre()
        );
    }
}