package mx.edu.tecdesoftware.librarybackend.persistence;

import mx.edu.tecdesoftware.librarybackend.domain.Client;
import mx.edu.tecdesoftware.librarybackend.domain.repository.ClientRepository;
import mx.edu.tecdesoftware.librarybackend.persistence.crud.ClienteCrudRepository;
import mx.edu.tecdesoftware.librarybackend.persistence.entity.Cliente;
import mx.edu.tecdesoftware.librarybackend.persistence.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepositoryImpl implements ClientRepository {

    @Autowired
    private ClienteCrudRepository clienteCrudRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Client> getAll() {
        List<Cliente> clientes = (List<Cliente>) clienteCrudRepository.findAll();
        return clientMapper.toClients(clientes);
    }

    public Optional<Client> getClient(String clientId) {
        return clienteCrudRepository.findById(clientId).map(clientMapper::toClient);
    }

    public Client register(String id, String name, String lastName, String email, String phone, String rawPassword) {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNombre(name);
        cliente.setApellidos(lastName);
        cliente.setCorreoElectronico(email);
        cliente.setTelefono(phone);
        cliente.setPassword(passwordEncoder.encode(rawPassword));
        return clientMapper.toClient(clienteCrudRepository.save(cliente));
    }

    public void delete(String clientId) {
        clienteCrudRepository.deleteById(clientId);
    }
}