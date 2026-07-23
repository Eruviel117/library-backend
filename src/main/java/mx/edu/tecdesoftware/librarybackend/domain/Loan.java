// domain/Loan.java
package mx.edu.tecdesoftware.librarybackend.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Loan {
    private Integer loanId;
    private String clientId;
    private LocalDateTime loanDate;
    private String status;
    private List<LoanItem> items;

    public Integer getLoanId() { return loanId; }
    public void setLoanId(Integer loanId) { this.loanId = loanId; }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public LocalDateTime getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDateTime loanDate) { this.loanDate = loanDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<LoanItem> getItems() { return items; }
    public void setItems(List<LoanItem> items) { this.items = items; }
}