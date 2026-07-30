package mx.edu.tecdesoftware.librarybackend.domain.repository;

import mx.edu.tecdesoftware.librarybackend.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    List<Client> getAll();
    Optional<Client> getClient(String clientId);
    Client register(String id, String name, String lastName, String email, String phone, String rawPassword);
    void delete(String clientId);
}