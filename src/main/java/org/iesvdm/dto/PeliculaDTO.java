package org.iesvdm.dto;

import java.math.BigDecimal;
import java.util.Date;



import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.iesvdm.domain.Categoria;
import org.iesvdm.domain.Idioma;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

@Data
//Para generar un constructor con lombok con todos los args
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class PeliculaDTO {

	private long idPelicula;

	@NotEmpty(message = "{validation.titulo.notEmpty}")
	@Size(min = 3, message = "{validation.titulo.size}")
	private String titulo;

	@Size(max = 300, message = "{validation.descripcion.size}")
	private String descripcion;
	@DateTimeFormat(pattern="yyyy")
	private Date anyoLanzamiento;
	private long idIdioma;
	private long idIdiomaOriginal;
	private int duracionAlquiler;
	private BigDecimal rentalRate;

	@Positive(message = "{validation.duracion.positive}")
	@NotNull(message = "{validation.duracion.notNull}")
	private int duracion;

	@DecimalMin(value = "19.99", message = "{validation.replacementCost.min}")
	private BigDecimal replacementCost;
	private String clasificacion;
	private String caracteristicasEspeciales;
	private Date ultimaActualizacion;
	//Atributos Extras
	private Idioma idioma;
	private Idioma idiomaOriginal;
	private Categoria categoria;
	

	
}
