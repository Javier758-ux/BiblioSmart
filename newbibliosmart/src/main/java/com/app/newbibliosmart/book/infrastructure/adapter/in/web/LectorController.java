package com.app.newbibliosmart.book.infrastructure.adapter.in.web;

import com.app.newbibliosmart.book.application.LectorService;
import com.app.newbibliosmart.book.domain.Lector;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.CrearLectorRequest;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.LectorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lectores")
@RequiredArgsConstructor

public class LectorController {

    private final LectorService lectorService;

    @PostMapping
    public ResponseEntity<LectorResponse> registrar(@Valid @RequestBody CrearLectorRequest request) {
        Lector lector = lectorService.registrar(
                request.getIdUsuario(),
                request.getNroLector(),
                request.getDatosContacto()
        );
        LectorResponse response = new LectorResponse(
                lector.getIdLector(),
                lector.getNroLector(),
                lector.getDatosContacto(),
                lector.getFechaRegistro()
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @GetMapping
    public List<LectorResponse> listar() {
        return lectorService.listar().stream()
                .map(l -> new LectorResponse(l.getIdLector(), l.getNroLector(), l.getDatosContacto(), l.getFechaRegistro()))
                .toList();
    }
}
