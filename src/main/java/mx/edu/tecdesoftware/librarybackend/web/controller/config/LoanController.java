package mx.edu.tecdesoftware.librarybackend.web.controller.config;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.librarybackend.domain.Loan;
import mx.edu.tecdesoftware.librarybackend.domain.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
@Tag(name = "Loan", description = "Manage book loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all loans", description = "Return a list of all registered loans")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of loans")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Loan>> getAll(){
        return new ResponseEntity<>(loanService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Get loans by client ID", description = "Return all loans made by a specific client")
    @ApiResponse(responseCode = "200", description = "loans found for the client")
    @ApiResponse(responseCode = "404", description = "no loans found for the client")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Loan>> getByClientId(
            @Parameter(description = "ID of the client", example = "A001", required = true)
            @PathVariable String clientId){
        List<Loan> loans = loanService.getByClientId(clientId);
        if (loans == null || loans.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(loans);
    }

    @PostMapping("/save")
    @Operation(
            summary = "Save a new loan",
            description = "Register a new loan and return the created loan",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example loan", value = """
                                    {
                                    "clientId" : "A001",
                                    "loanDate" : "2026-07-28T09:15:00",
                                    "status" : "Activo",
                                    "items" : [
                                        {
                                        "bookId" : 1,
                                        "returned" : false
                                        }
                                    ]
                                    }
                                    """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "loan created")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Loan> save(@RequestBody Loan loan){
        return new ResponseEntity<>(loanService.save(loan), HttpStatus.CREATED);
    }
}