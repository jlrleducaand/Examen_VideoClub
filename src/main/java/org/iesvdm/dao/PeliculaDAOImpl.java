package org.iesvdm.dao;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.domain.Categoria;
import org.iesvdm.domain.Idioma;
import org.iesvdm.domain.Pelicula;
import org.iesvdm.dto.PeliculaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
					(titulo,descripcion,fecha_lanzamiento,id_idioma,duracion_alquiler,
					rental_rate,duracion,replacement_cost,ultima_actualizacion
					)
					VALUES
					(	?,	?,	DATE (?), ?,	?,	?, ?,	?,	DATE (?));
				""";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		//Con recuperación de id generado
		int rows = jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[] { "id_pelicula" });
			int idx = 1;
			ps.setString(idx++, pelicula.getTitulo());
			ps.setString(idx++, pelicula.getDescripcion());

			//Conversión de java.util.Date a java.sqlDate
			ps.setDate(idx++, new java.sql.Date(pelicula.getFecha_lanzamiento().getTime()));
			//
			ps.setLong(idx++, pelicula.getId_idioma());
			ps.setInt(idx++, pelicula.getDuracion_alquiler());
			ps.setBigDecimal(idx++, pelicula.getRental_rate());
			ps.setInt(idx++, pelicula.getDuracion());
			ps.setBigDecimal(idx++, pelicula.getReplacement_cost());
			ps.setDate(idx++, new java.sql.Date (pelicula.getUltima_actualizacion().getTime()));
			return ps;
		}, keyHolder);

		pelicula.setId_pelicula(keyHolder.getKey().intValue());

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
						rs.getDate("fecha_lanzamiento"),
						rs.getInt("id_idioma"),
						rs.getInt("duracion_alquiler"),
						rs.getBigDecimal("rental_rate"),
						rs.getInt("duracion"),
						rs.getBigDecimal("replacement_cost"),
						rs.getTimestamp("ultima_actualizacion")));

		return listPeliculas;
	}

	@Override
	public List<PeliculaDTO> findAllHorrorDTO() {

		List<PeliculaDTO> listPeliculaDTOs = this.jdbcTemplate.query(
				"""
						SELECT p.id_pelicula, p.titulo, p.descripcion, p.fecha_lanzamiento, p.id_idioma, p.duracion_alquiler, p.rental_rate, p.duracion, p.replacement_cost, p.ultima_actualizacion	
						FROM pelicula p
      						Left JOIN pelicula_categoria pc ON p.id_pelicula = pc.id_pelicula
      						Left JOIN categoria c ON pc.id_categoria = c.id_categoria
     						WHERE c.nombre = 'Horror';

						""",
				(rs, rownum) -> new PeliculaDTO(
						rs.getInt("id_pelicula"),
						rs.getString("titulo"),
						rs.getString("descripcion"),
						rs.getDate("fecha_lanzamiento"),
						rs.getInt("id_idioma"),
						rs.getInt("duracion_alquiler"),
						rs.getBigDecimal("rental_rate"),
						rs.getInt("duracion"),
						rs.getBigDecimal("replacement_cost"),
						rs.getTimestamp("ultima_actualizacion")));

		RowMapper<PeliculaDTO> rowMapper = new BeanPropertyRowMapper<>(PeliculaDTO.class);

		return listPeliculaDTOs;
	}

	@Override
	public Map<String, Integer> findPelisPorCategoria() {
		Map<String, Integer> peliculasPorCategoria = new HashMap<>();

		String sql = "SELECT c.nombre AS nombre_categoria, COUNT(pc.id_pelicula) AS cantidad_peliculas " +
				"FROM categoria c " +
				"JOIN pelicula_categoria pc ON c.id_categoria = pc.id_categoria " +
				"GROUP BY c.nombre";

		jdbcTemplate.query(sql, rs -> {
			String nombreCategoria = rs.getString("nombre_categoria");
			int cantidadPeliculas = rs.getInt("cantidad_peliculas");
			peliculasPorCategoria.put(nombreCategoria, cantidadPeliculas);
		});

		return peliculasPorCategoria;
	}
}



