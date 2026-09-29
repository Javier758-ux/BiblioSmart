package com.app.newbibliosmart.book.infrastructure.adapter.in.web;

import com.app.newbibliosmart.book.application.RolService;
import com.app.newbibliosmart.book.domain.Rol;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.CrearRolRequest;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.RolResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/roles") @RequiredArgsConstructor
public class RolController {
    private final RolService rolService;
    @PostMapping
    public ResponseEntity<RolResponse> registrar(@Valid @RequestBody CrearRolRequest request) {
        Rol rol = rolService.registrar(request.getNombreRol(), request.getDescripcion()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirAResponse(rol));
    }

    @GetMapping
    public List<RolResponse> listar() {
        return rolService.listar()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public RolResponse buscarPorId(@PathVariable Long id) {
        Rol rol = rolService.buscarPorId(id);
        return convertirAResponse(rol);
    }

    private RolResponse convertirAResponse(Rol rol) {
        return new RolResponse(rol.getIdRol(), rol.getNombreRol(), rol.getDescripcion()
        );
    }
}