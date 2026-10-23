package utez.edu.mx.libros.repository.Prestamo;
import utez.edu.mx.libros.model.Lector.lector;
import utez.edu.mx.libros.model.Prestamo.prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<prestamo, Long> {


    int countByLectorAndEstadoIn(lector lector, List<String> estados);


    List<prestamo> findByLectorAndEstadoIn(lector lector, List<String> estados);
}