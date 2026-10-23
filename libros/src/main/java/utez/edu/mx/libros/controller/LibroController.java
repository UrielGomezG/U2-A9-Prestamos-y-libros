package utez.edu.mx.libros.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utez.edu.mx.libros.DTO.Libro.CreateLibroDto;
import utez.edu.mx.libros.model.Libro.libro;
import utez.edu.mx.libros.service.LibroService;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    public ResponseEntity<libro> crearLibro(@Valid @RequestBody CreateLibroDto dto) {
        return new ResponseEntity<>(libroService.crearLibro(dto), HttpStatus.CREATED);
    }
}
