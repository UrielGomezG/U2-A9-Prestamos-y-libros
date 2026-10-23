package utez.edu.mx.libros.service;

import utez.edu.mx.libros.DTO.Libro.CreateLectorDto;
import utez.edu.mx.libros.exception.LectorNoEncontradoException;
import utez.edu.mx.libros.model.Lector.lector;
import utez.edu.mx.libros.repository.Lector.LectorRepository;
import org.springframework.stereotype.Service;

@Service
public class LectorService {

    private final LectorRepository lectorRepository;

    public LectorService(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    public lector crearLector(CreateLectorDto dto) {
        lector lector = new lector();
        lector.setNombre(dto.getNombre());
        lector.setActivo(true);
        lector.setMembresiaPremium(dto.isMembresiaPremium());
        return lectorRepository.save(lector);
    }

    public lector rehabilitarLector(Long lectorId) {
        lector lector = lectorRepository.findById(lectorId)
                .orElseThrow(() -> new LectorNoEncontradoException("El lector con ID " + lectorId + " no existe"));

        lector.setActivo(true);
        return lectorRepository.save(lector);
    }
}