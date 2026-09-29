package com.app.newbibliosmart.book.infrastructure.adapter.in.web;

import com.app.newbibliosmart.book.infrastructure.adapter.out.persistence.LibroRepository;
import com.app.newbibliosmart.book.domain.Libro;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.DisponibilidadLibroResponse;
import com.app.newbibliosmart.book.application.EjemplarService;
import com.app.newbibliosmart.book.domain.Ejemplar;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.CrearEjemplarRequest;
import com.app.newbibliosmart.book.infrastructure.adapter.in.web.dto.EjemplarResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ejemplares")
@RequiredArgsConstructor
public class EjemplarController {
    private final EjemplarService ejemplarService;
    private final LibroRepository libroRepository;
    @PostMapping
    public ResponseEntity<EjemplarResponse> registrar(
            @Valid @RequestBody CrearEjemplarRequest request) {

        Ejemplar ejemplar = ejemplarService.registrar(
                request.getCodigo(),
                request.getIdLibro()
        );

        EjemplarResponse response = convertirAResponse(ejemplar);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public List<EjemplarResponse> listar() {

        return ejemplarService.listar()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }
    @GetMapping("/{id}")
    public EjemplarResponse buscarPorId(@PathVariable Long id) {

        Ejemplar ejemplar = ejemplarService.buscarPorId(id);

        return convertirAResponse(ejemplar);
    }
    @GetMapping("/disponibilidad/libro/{idLibro}")
    public DisponibilidadLibroResponse consultarDisponibilidad(
            @PathVariable Long idLibro) {

        Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Libro no encontrado con ID: " + idLibro
                        ));

        long totalEjemplares =
                ejemplarService.contarEjemplaresPorLibro(idLibro);

        long ejemplaresDisponibles =
                ejemplarService.contarEjemplaresDisponibles(idLibro);

        boolean disponible = ejemplaresDisponibles > 0;

        return new DisponibilidadLibroResponse(
                libro.getIdLibro(),
                libro.getTitulo(),
                totalEjemplares,
                ejemplaresDisponibles,
                disponible
        );
    }

    private EjemplarResponse convertirAResponse(Ejemplar ejemplar) {
        return new EjemplarResponse(
                ejemplar.getIdEjemplar(),
                ejemplar.getCodigo(),
                ejemplar.getEstado(),
                ejemplar.getLibro().getIdLibro(),
                ejemplar.getLibro().getTitulo()
        );
    }
}