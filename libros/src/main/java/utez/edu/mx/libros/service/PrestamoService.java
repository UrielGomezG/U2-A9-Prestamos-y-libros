package utez.edu.mx.libros.service;

import org.springframework.stereotype.Service;
import utez.edu.mx.libros.DTO.Prestamo.SolicitarPrestamoDto;
import utez.edu.mx.libros.exception.*;
import utez.edu.mx.libros.model.Lector.lector;
import utez.edu.mx.libros.repository.Lector.LectorRepository;
import utez.edu.mx.libros.repository.Libro.LibroRepository;
import utez.edu.mx.libros.repository.Prestamo.PrestamoRepository;
import utez.edu.mx.libros.model.Prestamo.prestamo;
import utez.edu.mx.libros.model.Libro.libro;
import utez.edu.mx.libros.model.Lector.lector;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoService {
        private final PrestamoRepository prestamoRepository;
        private final LibroRepository libroRepository;
        private final LectorRepository lectorRepository;

        public PrestamoService(
                PrestamoRepository prestamoRepository,
                LibroRepository libroRepository,
                LectorRepository lectorRepository
        ){
            this.lectorRepository = lectorRepository;
            this.libroRepository = libroRepository;
            this.prestamoRepository = prestamoRepository;
        }
    public prestamo solicitarPrestamo(SolicitarPrestamoDto dto) {
        lector lector = lectorRepository.findById(dto.getLectorId())
                .orElseThrow(() -> new LectorNoEncontradoException("El lector ingresado no existe"));

        if (!lector.isActivo()) {
            throw new LectorInactivoException("El lector está inactivo o bloqueado");
        }

        libro libro = libroRepository.findById(dto.getLibroId())
                .orElseThrow(() -> new LibroNoEncontradoException("El libro ingresado no existe"));

        if (libro.getEjemplaresDisponibles() < 1) {
            throw new SinEjemplaresDisponiblesException("No hay ejemplares disponibles para este libro");
        }

        int limite = lector.isMembresiaPremium() ? 5 : 3;
        int prestamosActivos = prestamoRepository.countByLectorAndEstadoIn(
                lector, List.of("PRESTADO", "VENCIDO")
        );

        if (prestamosActivos >= limite) {
            throw new LimitePrestamosSuperadoException(
                    "El lector ha alcanzado su límite máximo de préstamos activos (" + limite + ")"
            );
        }

        libro.setEjemplaresDisponibles(libro.getEjemplaresDisponibles() - 1);
        libroRepository.save(libro);

        prestamo prestamo = new prestamo();
        prestamo.setLector(lector);
        prestamo.setLibro(libro);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucionMaxima(LocalDate.now().plusDays(14));
        prestamo.setEstado("PRESTADO");

        return prestamoRepository.save(prestamo);
    }

    public prestamo devolverLibro(Long prestamoId) {
        prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new PrestamoNoEncontradoException("El préstamo con ID " + prestamoId + " no existe"));

        if ("DEVUELTO".equals(prestamo.getEstado())) {
            throw new EstadoPrestamoInvalidoException("El libro ya ha sido devuelto anteriormente");
        }

        prestamo.setEstado("DEVUELTO");

        libro libro = prestamo.getLibro();
        libro.setEjemplaresDisponibles(libro.getEjemplaresDisponibles() + 1);
        libroRepository.save(libro);

        if (LocalDate.now().isAfter(prestamo.getFechaDevolucionMaxima())) {
            lector lector = prestamo.getLector();
            lector.setActivo(false);
            lectorRepository.save(lector);
        }

        return prestamoRepository.save(prestamo);
    }

    public prestamo renovarPrestamo(Long prestamoId) {
        prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new PrestamoNoEncontradoException("El préstamo con ID " + prestamoId + " no existe"));

        if ("VENCIDO".equals(prestamo.getEstado()) || "DEVUELTO".equals(prestamo.getEstado())) {
            throw new EstadoPrestamoInvalidoException("No se puede renovar un préstamo que está VENCIDO o DEVUELTO");
        }

        prestamo.setFechaDevolucionMaxima(prestamo.getFechaDevolucionMaxima().plusDays(7));
        return prestamoRepository.save(prestamo);
    }

    public List<prestamo> listarPrestamosActivosLector(Long lectorId) {
        lector lector = lectorRepository.findById(lectorId)
                .orElseThrow(() -> new LectorNoEncontradoException("El lector ingresado no existe"));

        return prestamoRepository.findByLectorAndEstadoIn(lector, List.of("PRESTADO", "VENCIDO"));
    }

}
