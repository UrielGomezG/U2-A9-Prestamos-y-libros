package utez.edu.mx.libros.model.Prestamo;

import utez.edu.mx.libros.model.Lector.lector;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.libros.model.Libro.libro;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prestamo")
public class prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "lector_id", nullable = false)
    private lector lector;
    @ManyToOne(optional = false)
    @JoinColumn(name = "libro_id", nullable = false)
    private libro libro;
    @Column(nullable = false)
    private LocalDate fechaPrestamo;
    @Column (nullable = false)
    private LocalDate fechaDevolucionMaxima;
    @Column(nullable = false)
    private String estado;

}
