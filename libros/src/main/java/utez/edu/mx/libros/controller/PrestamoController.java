package utez.edu.mx.libros.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.libros.DTO.Prestamo.SolicitarPrestamoDto;
import utez.edu.mx.libros.model.Prestamo.prestamo;
import utez.edu.mx.libros.service.PrestamoService;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public ResponseEntity<prestamo> solicitarPrestamo(@Valid @RequestBody SolicitarPrestamoDto dto) {
        return new ResponseEntity<>(prestamoService.solicitarPrestamo(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/devolver")
    public ResponseEntity<prestamo> devolverLibro(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.devolverLibro(id));
    }

    @PatchMapping("/{id}/renovar")
    public ResponseEntity<prestamo> renovarPrestamo(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.renovarPrestamo(id));
    }

    @GetMapping("/lector/{lectorId}")
    public ResponseEntity<List<prestamo>> listarPrestamosActivosLector(@PathVariable Long lectorId) {
        return ResponseEntity.ok(prestamoService.listarPrestamosActivosLector(lectorId));
    }
}