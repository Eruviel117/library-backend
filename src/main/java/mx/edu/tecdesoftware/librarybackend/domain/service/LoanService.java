
package mx.edu.tecdesoftware.librarybackend.domain.service;

import mx.edu.tecdesoftware.librarybackend.domain.Loan;
import mx.edu.tecdesoftware.librarybackend.domain.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public List<Loan> getAll() {
        return loanRepository.getAll();
    }

    public List<Loan> getByClientId(String clientId) {
        return loanRepository.getByClientId(clientId);
    }

    public Loan save(Loan loan) {
        return loanRepository.save(loan);
    }
}