package utez.edu.mx.libros.DTO.Libro;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateLectorDto {

    @NotBlank(message = "El nombre del lector no puede estar vacío")
    private String nombre;

    private boolean membresiaPremium;
}