package mx.edu.tecdesoftware.librarybackend.domain.service;

import mx.edu.tecdesoftware.librarybackend.domain.Client;
import mx.edu.tecdesoftware.librarybackend.domain.ClientRegistrationRequest;
import mx.edu.tecdesoftware.librarybackend.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(String clientId) {
        return clientRepository.getClient(clientId);
    }

    public Client register(ClientRegistrationRequest request) {
        return clientRepository.register(
                request.getId(), request.getName(), request.getLastName(),
                request.getEmail(), request.getPhone(), request.getPassword()
        );
    }

    public boolean delete(String clientId) {
        if (getClient(clientId).isPresent()) {
            clientRepository.delete(clientId);
            return true;
        }
        return false;
    }
}