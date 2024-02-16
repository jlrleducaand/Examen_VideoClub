package org.iesvdm.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pelicula {
	
	private long id_pelicula;


	private String titulo;

	@Length(max=300, message="{msg.valid.maxLenght}")
	private String descripcion;

	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date fecha_lanzamiento;
	private long id_idioma;
	private int duracion_alquiler;
	private BigDecimal rental_rate;

	@NotBlank(message="{msg.valid.blank}")
	@NotNull(message="	{msg.valid.null}")
	@DecimalMin(value = "1", inclusive = true,  message="{msg.valid.min}")
	private int duracion;

	@Pattern(regexp = "(^\\d+\\.\\d{2}$\n)", message="{msg.valid.pattern}")
	@DecimalMin(value="19.90", inclusive=true, message="{msg.valid.min}")
	@DecimalMax(value="1000.00", message="{msg.valid.max}")
	private BigDecimal replacement_cost;

	private Date ultima_actualizacion;

	public Pelicula(int id_pelicula, String titulo, java.sql.Date fecha_lanzamiento, int id_idioma, int duracion_alquiler, BigDecimal rentalRate, int duracion, BigDecimal replacement_cost, Timestamp ultima_actualizacion) {
	this.id_pelicula = id_pelicula;
	this.titulo = titulo;
	this.fecha_lanzamiento = fecha_lanzamiento;
	this.id_idioma = id_idioma;
	this.duracion_alquiler = duracion_alquiler;
	this.rental_rate = rentalRate;
	this.duracion = duracion;
	this.replacement_cost=replacement_cost;
	this.ultima_actualizacion = ultima_actualizacion;
	}
}
