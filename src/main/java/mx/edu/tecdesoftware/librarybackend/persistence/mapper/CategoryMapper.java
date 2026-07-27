
package mx.edu.tecdesoftware.librarybackend.persistence.mapper;

import mx.edu.tecdesoftware.librarybackend.domain.Category;
import mx.edu.tecdesoftware.librarybackend.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mappings({
            @Mapping(source = "idCategoria",target = "categoryId"),
            @Mapping(source = "nombre",target = "name")
    })


    Category toCategory(Categoria categoria);

    @InheritInverseConfiguration
    Categoria toCategoria(Category category);
}