package mx.edu.tecdesoftware.librarybackend.security;

import mx.edu.tecdesoftware.librarybackend.persistence.crud.ClienteCrudRepository;
import mx.edu.tecdesoftware.librarybackend.persistence.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private ClienteCrudRepository clienteCrudRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public Optional<String> login(String email, String rawPassword) {
        Optional<Cliente> clienteOpt = clienteCrudRepository.findByCorreoElectronico(email);

        if (clienteOpt.isEmpty()) {
            return Optional.empty();
        }

        Cliente cliente = clienteOpt.get();

        if (!passwordEncoder.matches(rawPassword, cliente.getPassword())) {
            return Optional.empty();
        }

        return Optional.of(jwtUtil.generateToken(email));
    }
}