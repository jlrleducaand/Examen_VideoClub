package org.iesvdm.dao;

import java.sql.PreparedStatement;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.domain.Categoria;
import org.iesvdm.domain.Idioma;
import org.iesvdm.domain.Pelicula;
import org.iesvdm.dto.PeliculaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class PeliculaDAOImpl implements PeliculaDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public synchronized void create(Pelicula pelicula) {

		String sqlInsert = """
				INSERT INTO pelicula
					(titulo,descripcion,anyo_lanzamiento,id_idioma,id_idioma_original,duracion_alquiler,
					rental_rate,duracion,replacement_cost,clasificacion,caracteristicas_especiales
					)
					VALUES
					(	?,	?,	YEAR(?), ?,	?,	?, ?, ?, ?,	?,	?	);
				""";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		//Con recuperación de id generado
		int rows = jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[] { "id_pelicula" });
			int idx = 1;
			ps.setString(idx++, pelicula.getTitulo());
			ps.setString(idx++, pelicula.getDescripcion());

			//Conversión de java.util.Date a java.sqlDate
			ps.setDate(idx++, new java.sql.Date(pelicula.getAnyoLanzamiento().getTime()));
			//
			ps.setLong(idx++, pelicula.getIdIdioma());
			ps.setLong(idx++, pelicula.getIdIdiomaOriginal());
			ps.setInt(idx++, pelicula.getDuracionAlquiler());
			ps.setBigDecimal(idx++, pelicula.getRentalRate());
			ps.setInt(idx++, pelicula.getDuracion());
			ps.setBigDecimal(idx++, pelicula.getReplacementCost());
			ps.setString(idx++, pelicula.getClasificacion());
			ps.setString(idx++, pelicula.getCaracteristicasEspeciales());
			return ps;
		}, keyHolder);

		pelicula.setIdPelicula(keyHolder.getKey().intValue());

		/*
		Sin recuperación de id generado
		int rows = jdbcTemplate.update(sqlInsert,
							cliente.getNombre(),
							cliente.getApellido1(),
							cliente.getApellido2(),
							cliente.getCiudad(),
							cliente.getCategoria()
					);
					*/

		log.info("Insertados {} registros.", rows);
	}

	@Override
	public List<Pelicula> findAll() {

		List<Pelicula> listPeliculas = this.jdbcTemplate.query(
				"select * from pelicula",
				(rs, rownum) -> new Pelicula(rs.getInt("id_pelicula"),
						rs.getString("titulo"),
						rs.getString("descripcion"),
						rs.getDate("anyo_lanzamiento"),
						rs.getInt("id_idioma"),
						rs.getInt("id_idioma_original"),
						rs.getInt("duracion"),
						rs.getBigDecimal("rental_rate"),
						rs.getInt("duracion"),
						rs.getBigDecimal("replacement_cost"),
						rs.getString("clasificacion"),
						rs.getString("caracteristicas_especiales"),
						rs.getTimestamp("ultima_actualizacion")));

		return listPeliculas;
	}

	@Override
	public List<PeliculaDTO> findAllDTO() {

		List<PeliculaDTO> listPeliculaDTOs = this.jdbcTemplate.query(
				"""
						select P.*, C.id_categoria as idCat, C.nombre as nomCat, I.id_idioma as idIdiom, I.nombre as nomIdiom, IO.id_idioma as idIdiomOrig, IO.nombre as nomIdiomOrig from pelicula P
										left join pelicula_categoria P_C on P.id_pelicula = P_C.id_pelicula
										left join categoria C on C.id_categoria = P_C.id_categoria
										left join idioma I on P.id_idioma = I.id_idioma
						                left join idioma IO on P.id_idioma_original = I.id_idioma
						""",
				(rs, rowNum) -> new PeliculaDTO(
						rs.getInt("id_pelicula"),
						rs.getString("titulo"),
						rs.getString("descripcion"),
						rs.getDate("anyo_lanzamiento"),
						rs.getInt("id_idioma"),
						rs.getInt("id_idioma_original"),
						rs.getInt("duracion"),
						rs.getBigDecimal("rental_rate"),
						rs.getInt("duracion"),
						rs.getBigDecimal("replacement_cost"),
						rs.getString("clasificacion"),
						rs.getString("caracteristicas_especiales"),
						rs.getTimestamp("ultima_actualizacion"),
						Idioma.builder().id(rs.getLong("idIdiom")).nombre(rs.getString("nomIdiom")).build(),
						Idioma.builder().id(rs.getLong("idIdiomOrig")).nombre(rs.getString("nomIdiomOrig")).build(),
						Categoria.builder().id(rs.getLong("idCat")).nombre(rs.getString("nomCat")).build()));

		return listPeliculaDTOs;
	}



}
