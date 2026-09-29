package com.app.newbibliosmart.book.infrastructure.adapter.in.web;

import com.app.newbibliosmart.book.application.EditorialService;
import com.app.newbibliosmart.book.domain.Editorial;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.CrearEditorialRequest;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.EditorialResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/editoriales") @RequiredArgsConstructor
public class EditorialController {

    private final EditorialService editorialService;
    @PostMapping
    public ResponseEntity<EditorialResponse> registrar(
            @Valid @RequestBody CrearEditorialRequest request) {
        Editorial editorial = editorialService.registrar(request.getNombre());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(convertirAResponse(editorial));
    }

    @GetMapping
    public List<EditorialResponse> listar() {
        return editorialService.listar()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public EditorialResponse buscarPorId(@PathVariable Long id) {
        Editorial editorial = editorialService.buscarPorId(id);
        return convertirAResponse(editorial);
    }

    private EditorialResponse convertirAResponse(Editorial editorial) {
        return new EditorialResponse(
                editorial.getId(),
                editorial.getNombre()
        );
    }
}