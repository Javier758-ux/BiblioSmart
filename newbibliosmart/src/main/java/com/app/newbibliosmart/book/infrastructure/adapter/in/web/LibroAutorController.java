package com.app.newbibliosmart.book.infrastructure.adapter.in.web;

import com.app.newbibliosmart.book.application.LibroAutorService;
import com.app.newbibliosmart.book.domain.LibroAutor;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.CrearLibroAutorRequest;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.LibroAutorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros-autores")
@RequiredArgsConstructor
public class LibroAutorController {
    private final LibroAutorService libroAutorService;

    @PostMapping
    public ResponseEntity<LibroAutorResponse> registrar(
            @Valid @RequestBody CrearLibroAutorRequest request) {

        LibroAutor libroAutor = libroAutorService.registrar(
                request.getIdLibro(),
                request.getIdAutor()
        );

        LibroAutorResponse response = convertirAResponse(libroAutor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public List<LibroAutorResponse> listar() {

        return libroAutorService.listar()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public LibroAutorResponse buscarPorId(@PathVariable Long id) {

        LibroAutor libroAutor = libroAutorService.buscarPorId(id);

        return convertirAResponse(libroAutor);
    }

    private LibroAutorResponse convertirAResponse(LibroAutor libroAutor) {

        return new LibroAutorResponse(
                libroAutor.getIdLibroAutor(),
                libroAutor.getLibro().getIdLibro(),
                libroAutor.getLibro().getTitulo(),
                libroAutor.getAutor().getIdAutor(),
                libroAutor.getAutor().getNombre()
        );
    }
}
