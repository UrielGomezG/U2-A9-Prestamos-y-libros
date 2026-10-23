package utez.edu.mx.libros.service;

import utez.edu.mx.libros.DTO.Libro.CreateLibroDto;
import utez.edu.mx.libros.model.Libro.libro;
import utez.edu.mx.libros.repository.Libro.LibroRepository;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public libro crearLibro(CreateLibroDto dto) {
        libro libro = new libro();
        libro.setTitulo(dto.getTitulo());
        libro.setEjemplaresDisponibles(dto.getEjemplaresDisponibles());
        return libroRepository.save(libro);
    }
}