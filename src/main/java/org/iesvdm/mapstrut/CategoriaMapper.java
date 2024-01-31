package org.iesvdm.mapstrut;


import org.iesvdm.domain.Categoria;
import org.iesvdm.dto.CategoriaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface CategoriaMapper {

    public CategoriaDTO categoriaACategoriaDTO(Categoria categoria);

    public Categoria categoriaDTOACategoria(CategoriaDTO categoriaDTO);


}
