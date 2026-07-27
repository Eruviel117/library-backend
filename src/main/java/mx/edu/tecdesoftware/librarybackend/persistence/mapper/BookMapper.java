
package mx.edu.tecdesoftware.librarybackend.persistence.mapper;

import mx.edu.tecdesoftware.librarybackend.domain.Book;
import mx.edu.tecdesoftware.librarybackend.persistence.entity.Libro;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface BookMapper {

    @Mappings({
            @Mapping(source = "idLibro", target = "bookId"),
            @Mapping(source = "titulo", target = "title"),
            @Mapping(source = "autor", target = "author"),
            @Mapping(source = "isbn", target = "isbn"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "cantidadDisponible", target = "availableQuantity"),
            @Mapping(source = "disponible", target = "available"),
            @Mapping(source = "categoria", target = "category")
    })
    Book toBook(Libro libro);
    List<Book> toBooks(List<Libro> libros);

    @InheritInverseConfiguration
    @Mapping(target = "categoria", ignore = true)
    Libro toLibro(Book book);
}