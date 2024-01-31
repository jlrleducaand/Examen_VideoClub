package org.iesvdm.dto;

import java.util.Date;
import java.util.Objects;

import org.iesvdm.domain.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class CategoriaDTO{

	private long id;
	private String nombre;
	private Date ultimaActualizacion;

	private int conteoPeliculas;

	
	
}
