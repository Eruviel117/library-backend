
package mx.edu.tecdesoftware.librarybackend.persistence.mapper;

import mx.edu.tecdesoftware.librarybackend.domain.Loan;
import mx.edu.tecdesoftware.librarybackend.persistence.entity.DetallePrestamo;
import mx.edu.tecdesoftware.librarybackend.persistence.entity.Prestamo;
import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LoanItemMapper.class})
public abstract class LoanMapper {

    @Mappings({
            @Mapping(source = "idPrestamo", target = "loanId"),

            @Mapping(source = "idCliente", target = "clientId"),
            @Mapping(source = "fechaPrestamo", target = "loanDate"),
            @Mapping(source = "estado", target = "status"),

            @Mapping(source = "detalles", target = "items")
    })
    public abstract Loan toLoan(Prestamo prestamo);

    public abstract List<Loan> toLoans(List<Prestamo> prestamos);



    @InheritInverseConfiguration
    public abstract Prestamo toPrestamo(Loan loan);

    @AfterMapping
    protected void linkDetalles(@MappingTarget Prestamo prestamo) {
        if (prestamo.getDetalles() == null) {
            return;

        }
        for (DetallePrestamo detalle : prestamo.getDetalles()) {
            detalle.setPrestamo(prestamo);
        }
    }
}