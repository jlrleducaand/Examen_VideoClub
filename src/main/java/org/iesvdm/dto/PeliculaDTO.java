package org.iesvdm.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.*;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import org.iesvdm.domain.Idioma;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

@Data
//Para generar un constructor con lombok con todos los args
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class PeliculaDTO {

	private long id_pelicula;

	@NotBlank(message="{msg.valid.blank}")
	@NotNull(message="	{msg.valid.null}")
	@Length(min=3, message="{msg.valid.maxLenght}")
	private String titulo;

	@Size(max = 300, message = "{validation.descripcion.size}")
	private String descripcion;

	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date fecha_lanzamiento;
	private long id_idioma;
	private int duracion_alquiler;

	@DecimalMin(value = "0.00", inclusive = true, message = "{msg.valid.min}")
	private BigDecimal rental_rate;

	@Positive(message = "{validation.duracion.positive}")
	@NotNull(message = "{validation.duracion.notNull}")
	private int duracion;

	@DecimalMin(value = "19.99", message = "{validation.replacementCost.min}")
	private BigDecimal replacement_cost;

	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date ultima_actualizacion;


	//Atributos Extras
	private  int peliculas_totales;
	private  double coste_remplazo_horror;
	private  Map<String,Integer> peliculas_por_categoria;
	//private List<Idioma> listaIdiomas;


	public PeliculaDTO(int id_pelicula, String titulo,String descripcion, java.sql.Date fecha_lanzamiento, int id_idioma, int duracion, BigDecimal rentalRate, int duracion_alquiler, BigDecimal replacement_cost, Timestamp ultima_actualizacion) {
		this.id_pelicula = id_pelicula;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fecha_lanzamiento = fecha_lanzamiento;
		this.id_idioma = id_idioma;
		this.duracion = duracion;
		this.rental_rate = rentalRate;
		this.duracion_alquiler = duracion_alquiler;
		this.replacement_cost=replacement_cost;
		this.ultima_actualizacion = ultima_actualizacion;
	}


	public PeliculaDTO(int idPelicula, String titulo, BigDecimal replacementCost) {
		this.id_pelicula = idPelicula;
		this.titulo = titulo;
		this.replacement_cost = replacementCost;
	}
}
