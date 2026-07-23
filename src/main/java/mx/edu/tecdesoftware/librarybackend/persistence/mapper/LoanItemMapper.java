// persistence/mapper/LoanItemMapper.java
package mx.edu.tecdesoftware.librarybackend.persistence.mapper;

import mx.edu.tecdesoftware.librarybackend.domain.LoanItem;
import mx.edu.tecdesoftware.librarybackend.persistence.entity.DetallePrestamo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoanItemMapper {

    @Mapping(source = "idLibro", target = "bookId")
    @Mapping(source = "fechaDevolucion", target = "returnDate")
    @Mapping(source = "devuelto", target = "returned")


    LoanItem toLoanItem(DetallePrestamo detallePrestamo);
    List<LoanItem> toLoanItems(List<DetallePrestamo> detalles);

    @Mapping(target = "idLibro", source = "bookId")
    @Mapping(target = "fechaDevolucion", source = "returnDate")
    @Mapping(target = "devuelto", source = "returned")
    @Mapping(target = "prestamo", ignore = true)
    @Mapping(target = "libro", ignore = true)
    @Mapping(target = "idDetalle", ignore = true)

    DetallePrestamo toDetallePrestamo(LoanItem loanItem);
    List<DetallePrestamo> toDetallePrestamos(List<LoanItem> items);
}