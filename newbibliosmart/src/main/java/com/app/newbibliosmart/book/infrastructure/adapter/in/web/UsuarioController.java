package com.app.newbibliosmart.book.infrastructure.adapter.in.web;

import com.app.newbibliosmart.book.application.UsuarioService;
import com.app.newbibliosmart.book.domain.Usuario;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.CrearUsuarioRequest;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.UsuarioResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/usuarios") @RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    @PostMapping
    public ResponseEntity<UsuarioResponse> registrar(
            @Valid @RequestBody CrearUsuarioRequest request) {
        Usuario usuario = usuarioService.registrar(request.getIdRol(), request.getNombreCompleto(),
                request.getCredenciales(), request.getEstado());
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirAResponse(usuario));
    }

    @GetMapping
    public List<UsuarioResponse> listar() {
        return usuarioService.listar().stream().map(this::convertirAResponse).toList();
    }

    @GetMapping("/{id}")
    public UsuarioResponse buscarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return convertirAResponse(usuario);
    }

    @GetMapping("/rol/{idRol}")
    public List<UsuarioResponse> listarPorRol(
            @PathVariable Long idRol) {

        return usuarioService.listarPorRol(idRol)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    private UsuarioResponse convertirAResponse(Usuario usuario) {

        return new UsuarioResponse(
                usuario.getIdUsuario(),
                usuario.getRol().getIdRol(),
                usuario.getRol().getNombreRol(),
                usuario.getNombreCompleto(),
                usuario.getEstado()
        );
    }
}