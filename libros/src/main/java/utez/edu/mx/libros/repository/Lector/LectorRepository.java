package utez.edu.mx.libros.repository.Lector;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.libros.model.Lector.lector;

public interface LectorRepository extends JpaRepository<lector, Long> {
}
