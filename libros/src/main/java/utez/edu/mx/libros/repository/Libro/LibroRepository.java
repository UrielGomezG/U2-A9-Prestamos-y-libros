package utez.edu.mx.libros.repository.Libro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.mx.libros.model.Libro.libro;

@Repository
public interface LibroRepository extends JpaRepository<libro, Long> {
}
