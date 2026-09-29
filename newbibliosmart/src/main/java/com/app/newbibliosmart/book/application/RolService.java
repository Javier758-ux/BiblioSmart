package com.app.newbibliosmart.book.application;

import com.app.newbibliosmart.book.domain.Rol;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
public class RolService {
    private final RolRepository rolRepository;
    @Transactional
    public Rol registrar(String nombreRol, String descripcion) {
        Rol rol = new Rol();
        rol.setNombreRol(nombreRol);
        rol.setDescripcion(descripcion);
        return rolRepository.save(rol);
    }

    @Transactional(readOnly = true)
    public List<Rol> listar() {
        return rolRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Rol buscarPorId(Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Rol no encontrado con ID: " + id
                        )
                );
    }
}