
package mx.edu.tecdesoftware.librarybackend.domain.repository;

import mx.edu.tecdesoftware.librarybackend.domain.Loan;

import java.util.List;

public interface LoanRepository {
    List<Loan> getAll();
    List<Loan> getByClientId(String clientId);
    Loan save(Loan loan);
}