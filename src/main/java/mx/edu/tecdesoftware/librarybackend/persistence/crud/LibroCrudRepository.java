package mx.edu.tecdesoftware.librarybackend.persistence.crud;
import mx.edu.tecdesoftware.librarybackend.persistence.entity.Libro;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LibroCrudRepository extends CrudRepository<Libro, Integer> {

    // SELECT * FROM libros WHERE id_categoria = ? ORDER BY titulo ASC
    List<Libro> findByIdCategoriaOrderByTituloAsc(Integer idCategoria);



    // SELECT * FROM libros WHERE disponible = true
    List<Libro> findByDisponibleTrue();
}