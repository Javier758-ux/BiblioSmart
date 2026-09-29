package com.app.newbibliosmart.book.infrastructure.adapter.in.web;

import com.app.newbibliosmart.book.application.AutorService;
import com.app.newbibliosmart.book.domain.Autor;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.AutorResponse;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.CrearAutorRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;

    @PostMapping
    public ResponseEntity<AutorResponse> registrar(
            @Valid @RequestBody CrearAutorRequest request) {

        Autor autor = autorService.registrar(request.getNombre());

        AutorResponse response = new AutorResponse(
                autor.getIdAutor(),
                autor.getNombre()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public List<AutorResponse> listar() {

        return autorService.listar()
                .stream()
                .map(autor -> new AutorResponse(
                        autor.getIdAutor(),
                        autor.getNombre()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public AutorResponse buscarPorId(@PathVariable Long id) {

        Autor autor = autorService.buscarPorId(id);

        return new AutorResponse(
                autor.getIdAutor(),
                autor.getNombre()
        );
    }
}