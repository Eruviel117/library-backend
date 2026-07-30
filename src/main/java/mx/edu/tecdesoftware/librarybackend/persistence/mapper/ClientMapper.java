package mx.edu.tecdesoftware.librarybackend.persistence.mapper;

import mx.edu.tecdesoftware.librarybackend.domain.Client;
import mx.edu.tecdesoftware.librarybackend.persistence.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mappings({
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "apellidos", target = "lastName"),
            @Mapping(source = "correoElectronico", target = "email"),
            @Mapping(source = "telefono", target = "phone")
    })
    Client toClient(Cliente cliente);
    List<Client> toClients(List<Cliente> clientes);
}