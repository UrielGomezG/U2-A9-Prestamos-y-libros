package utez.edu.mx.libros.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.libros.DTO.Libro.CreateLectorDto;
import utez.edu.mx.libros.model.Lector.lector;
import utez.edu.mx.libros.service.LectorService;

@RestController
@RequestMapping("/lectores")
public class LectorController {

    private final LectorService lectorService;

    public LectorController(LectorService lectorService) {
        this.lectorService = lectorService;
    }

    @PostMapping
    public ResponseEntity<lector> crearLector(@Valid @RequestBody CreateLectorDto dto) {
        return new ResponseEntity<>(lectorService.crearLector(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/rehabilitar")
    public ResponseEntity<lector> rehabilitarLector(@PathVariable Long id) {
        return ResponseEntity.ok(lectorService.rehabilitarLector(id));
    }
}