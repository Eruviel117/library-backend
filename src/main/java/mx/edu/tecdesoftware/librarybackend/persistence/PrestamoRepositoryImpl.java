
package mx.edu.tecdesoftware.librarybackend.persistence;

import mx.edu.tecdesoftware.librarybackend.domain.Loan;
import mx.edu.tecdesoftware.librarybackend.domain.repository.LoanRepository;
import mx.edu.tecdesoftware.librarybackend.persistence.crud.PrestamoCrudRepository;
import mx.edu.tecdesoftware.librarybackend.persistence.entity.Prestamo;
import mx.edu.tecdesoftware.librarybackend.persistence.mapper.LoanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PrestamoRepositoryImpl implements LoanRepository {

    @Autowired
    private PrestamoCrudRepository prestamoCrudRepository;

    @Autowired
    private LoanMapper loanMapper;

    public List<Loan> getAll() {
        List<Prestamo> prestamos = (List<Prestamo>) prestamoCrudRepository.findAll();
        return loanMapper.toLoans(prestamos);
    }

    public List<Loan> getByClientId(String clientId) {
        List<Prestamo> prestamos = prestamoCrudRepository.findByIdCliente(clientId);
        return loanMapper.toLoans(prestamos);
    }

    public Loan save(Loan loan) {
        Prestamo prestamo = loanMapper.toPrestamo(loan);
        return loanMapper.toLoan(prestamoCrudRepository.save(prestamo));
    }
}