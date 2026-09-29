package com.app.newbibliosmart.book.application;

import com.app.newbibliosmart.book.domain.Usuario;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.UsuarioRepository;
import com.app.newbibliosmart.book.domain.Lector;
import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.LectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service @RequiredArgsConstructor

public class LectorService {
    private final LectorRepository lectorRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Lector registrar(Long idUsuario, String nroLector, String datosContacto) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() ->
                        new IllegalArgumentException("Usuario no encontrado con ID: " + idUsuario));
        Lector lector = new Lector();
        lector.setUsuario(usuario);
        lector.setNroLector(nroLector);
        lector.setDatosContacto(datosContacto);
        lector.setFechaRegistro(LocalDate.now());
        return lectorRepository.save(lector);
    }
    @Transactional(readOnly = true)
    public List<Lector> listar() {
        return lectorRepository.findAll();
    }
}
