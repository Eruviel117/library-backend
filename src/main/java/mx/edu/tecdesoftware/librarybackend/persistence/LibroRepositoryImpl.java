
package mx.edu.tecdesoftware.librarybackend.persistence;

import mx.edu.tecdesoftware.librarybackend.domain.Book;
import mx.edu.tecdesoftware.librarybackend.domain.repository.BookRepository;
import mx.edu.tecdesoftware.librarybackend.persistence.crud.LibroCrudRepository;
import mx.edu.tecdesoftware.librarybackend.persistence.entity.Libro;
import mx.edu.tecdesoftware.librarybackend.persistence.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LibroRepositoryImpl implements BookRepository {

    @Autowired
    private LibroCrudRepository libroCrudRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<Book> getAll() {
        List<Libro> libros = (List<Libro>) libroCrudRepository.findAll();
        return bookMapper.toBooks(libros);
    }

    public Optional<List<Book>> getByCategory(int categoryId) {
        List<Libro> libros = libroCrudRepository.findByIdCategoriaOrderByTituloAsc(categoryId);
        return Optional.of(bookMapper.toBooks(libros));
    }

    public Optional<Book> getBook(int bookId) {
        return libroCrudRepository.findById(bookId).map(bookMapper::toBook);
    }

    public Book save(Book book) {
        Libro libro = bookMapper.toLibro(book);
        return bookMapper.toBook(libroCrudRepository.save(libro));
    }

    public void delete(int bookId) {
        libroCrudRepository.deleteById(bookId);
    }
}