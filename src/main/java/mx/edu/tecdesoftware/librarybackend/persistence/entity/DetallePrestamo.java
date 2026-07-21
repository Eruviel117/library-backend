
package mx.edu.tecdesoftware.librarybackend.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "detalle_prestamo")
public class DetallePrestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;

    @Column(name = "id_libro")
    private Integer idLibro;

    @Column(name = "fecha_devolucion")
    private LocalDate fechaDevolucion;

    @Column(name = "devuelto")
    private Boolean devuelto;

    @ManyToOne
    @JoinColumn(name = "id_prestamo")
    private Prestamo prestamo;

    @ManyToOne
    @JoinColumn(name = "id_libro", insertable = false, updatable = false)
    private Libro libro;

    public DetallePrestamo() {
    }

    public Integer getIdDetalle() { return idDetalle; }
    public void setIdDetalle(Integer idDetalle) { this.idDetalle = idDetalle; }

    public Integer getIdLibro() { return idLibro; }
    public void setIdLibro(Integer idLibro) { this.idLibro = idLibro; }

    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(LocalDate fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }

    public Boolean getDevuelto() { return devuelto; }
    public void setDevuelto(Boolean devuelto) { this.devuelto = devuelto; }

    public Prestamo getPrestamo() { return prestamo; }
    public void setPrestamo(Prestamo prestamo) { this.prestamo = prestamo; }

    public Libro getLibro() { return libro; }
    public void setLibro(Libro libro) { this.libro = libro; }
}