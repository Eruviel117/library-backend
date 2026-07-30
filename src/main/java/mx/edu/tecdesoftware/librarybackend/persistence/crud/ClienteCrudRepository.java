package mx.edu.tecdesoftware.librarybackend.persistence.crud;

import mx.edu.tecdesoftware.librarybackend.persistence.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClienteCrudRepository extends CrudRepository<Cliente, String> {
    Optional<Cliente> findByCorreoElectronico(String correoElectronico);
}