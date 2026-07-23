// domain/LoanItem.java
package mx.edu.tecdesoftware.librarybackend.domain;

import java.time.LocalDate;

public class LoanItem {
    private Integer bookId;
    private LocalDate returnDate;
    private Boolean returned;

    public Integer getBookId() { return bookId; }
    public void setBookId(Integer bookId) { this.bookId = bookId; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public Boolean getReturned() { return returned; }
    public void setReturned(Boolean returned) { this.returned = returned; }
}