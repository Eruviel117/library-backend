
package mx.edu.tecdesoftware.librarybackend.domain.repository;

import mx.edu.tecdesoftware.librarybackend.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> getAll();
    Optional<List<Book>> getByCategory(int categoryId);
    Optional<Book> getBook(int bookId);
    Book save(Book book);
    void delete(int bookId);
}