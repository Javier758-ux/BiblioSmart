package com.app.newbibliosmart.book.application;

import com.app.newbibliosmart.book.domain.Rol;
import com.app.newbibliosmart.book.domain.Usuario;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.RolRepository;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    @Transactional
    public Usuario registrar(Long idRol, String nombreCompleto, String credenciales, String estado) {
        Rol rol = rolRepository.findById(idRol).orElseThrow(() ->
                        new IllegalArgumentException("Rol no encontrado con ID: " + idRol));
        Usuario usuario = new Usuario();
        usuario.setRol(rol);
        usuario.setNombreCompleto(nombreCompleto);
        usuario.setCredenciales(credenciales);
        usuario.setEstado(estado);
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Usuario no encontrado con ID: " + id
                        )
                );
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarPorRol(Long idRol) {
        if (!rolRepository.existsById(idRol)) {
            throw new IllegalArgumentException("Rol no encontrado con ID: " + idRol);
        }
        return usuarioRepository.findByRol_IdRol(idRol);
    }
}