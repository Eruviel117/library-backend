
package mx.edu.tecdesoftware.librarybackend.domain.service;

import mx.edu.tecdesoftware.librarybackend.domain.Book;
import mx.edu.tecdesoftware.librarybackend.domain.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    public Optional<List<Book>> getByCategory(int categoryId) {
        return bookRepository.getByCategory(categoryId);
    }

    public Optional<Book> getBook(int bookId) {
        return bookRepository.getBook(bookId);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public boolean delete(int bookId) {
        if (getBook(bookId).isPresent()) {
            bookRepository.delete(bookId);
            return true;
        }
        return false;
    }
}