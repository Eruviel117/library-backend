package mx.edu.tecdesoftware.librarybackend.web.controller.config;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.librarybackend.domain.Client;
import mx.edu.tecdesoftware.librarybackend.domain.ClientRegistrationRequest;
import mx.edu.tecdesoftware.librarybackend.domain.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Tag(name = "Client", description = "Manage library clients and their registration")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("")
    @Operation(
            summary = "Get all clients",
            description = "Return a list of all registered clients (never includes the password)"
    )
    @ApiResponse(responseCode = "200", description = "Successful retrieval of clients")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Client>> getAll(){
        return ResponseEntity.ok(clientService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get client by ID", description = "Return a client by its ID if it exists")
    @ApiResponse(responseCode = "200", description = "client found")
    @ApiResponse(responseCode = "404", description = "client not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Client> getClient(
            @Parameter(description = "ID of the client", example = "A001", required = true)
            @PathVariable String id){
        return clientService.getClient(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(
            summary = "Register a new client",
            description = "Create a new client and encrypt its password before saving it",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example client", value = """
                                    {
                                    "id" : "A006",
                                    "name" : "Pedro",
                                    "lastName" : "Gomez",
                                    "email" : "pedro@correo.com",
                                    "phone" : "5599887766",
                                    "password" : "1234"
                                    }
                                    """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "client created")
    @ApiResponse(responseCode = "409", description = "client id already exists")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Client> register(@RequestBody ClientRegistrationRequest request){
        return new ResponseEntity<>(clientService.register(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a client by ID", description = "Delete a client if it exists")
    @ApiResponse(responseCode = "200", description = "client deleted")
    @ApiResponse(responseCode = "404", description = "client not found")
    @ApiResponse(responseCode = "409", description = "client is referenced by an existing loan")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the client to be deleted", example = "A006", required = true)
            @PathVariable String id){
        if (clientService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}