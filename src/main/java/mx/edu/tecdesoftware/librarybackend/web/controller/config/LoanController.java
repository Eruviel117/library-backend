// web/controller/config/LoanController.java
package mx.edu.tecdesoftware.librarybackend.web.controller.config;

import mx.edu.tecdesoftware.librarybackend.domain.Loan;
import mx.edu.tecdesoftware.librarybackend.domain.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Loan>> getAll(){
        return new ResponseEntity<>(loanService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Loan>> getByClientId(@PathVariable String clientId){
        List<Loan> loans = loanService.getByClientId(clientId);
        if (loans == null || loans.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(loans);
    }

    @PostMapping("/save")
    public ResponseEntity<Loan> save(@RequestBody Loan loan){
        return new ResponseEntity<>(loanService.save(loan), HttpStatus.CREATED);
    }
}