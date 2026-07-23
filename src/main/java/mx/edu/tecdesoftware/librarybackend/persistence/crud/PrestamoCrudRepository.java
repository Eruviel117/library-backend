package mx.edu.tecdesoftware.librarybackend.persistence.crud;

import mx.edu.tecdesoftware.librarybackend.persistence.entity.Prestamo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrestamoCrudRepository extends CrudRepository<Prestamo, Integer> {

    // SELECT * FROM prestamos WHERE id_cliente = ?
    List<Prestamo> findByIdCliente(String idCliente);
}