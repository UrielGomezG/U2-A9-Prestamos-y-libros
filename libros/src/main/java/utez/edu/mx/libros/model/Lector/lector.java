package utez.edu.mx.libros.model.Lector;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table
@Entity(name = "lector")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private boolean activo;
    @Column(nullable = false)
    private boolean membresiaPremium;
}
