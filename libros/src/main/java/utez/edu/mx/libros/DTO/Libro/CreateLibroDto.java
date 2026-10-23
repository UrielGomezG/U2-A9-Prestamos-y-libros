package utez.edu.mx.libros.DTO.Libro;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateLibroDto {

    @NotBlank(message = "El título del libro no puede estar vacío")
    private String titulo;

    @Min(value = 1, message = "Debe haber al menos 1 ejemplar disponible")
    private int ejemplaresDisponibles;
}