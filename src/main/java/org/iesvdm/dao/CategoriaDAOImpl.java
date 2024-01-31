package org.iesvdm.dao;

import java.util.List;

import org.iesvdm.domain.Categoria;
import org.iesvdm.dto.CategoriaDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriaDAOImpl implements CategoriaDAO{

	private JdbcTemplate jdbcTemplate;
	
	public CategoriaDAOImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override	
	public Categoria find(Long id) {
		
		Categoria categoria = this.jdbcTemplate.queryForObject("select * from categoria where id_categoria = ?"
										, (rs, rowNum) -> new Categoria(rs.getInt("id_categoria")
																		, rs.getString("nombre")
																		, rs.getDate("ultima_actualizacion"))
										, id);
		
		return categoria;
	}

	@Override
	public CategoriaDTO findDTO(Long id) {
		
		CategoriaDTO categoriaDTO = this.jdbcTemplate.queryForObject("""
					select C.*, count(P.id_pelicula) as conteoPelisCat from categoria C left join pelicula_categoria P_C on C.id_categoria = P_C.id_categoria 
					left join pelicula P on P_C.id_pelicula = P.id_pelicula where C.id_categoria = ? group by C.id_categoria 	
				"""
										, (rs, rowNum) -> new CategoriaDTO(rs.getInt("id_categoria")
																		, rs.getString("nombre")
																		, rs.getDate("ultima_actualizacion")
																		, rs.getInt("conteoPelisCat"))
										, id);
		
		return categoriaDTO;
	}

	@Override
	public List<Categoria> findAll() {
		
		List<Categoria> listaCategoria = this.jdbcTemplate.query("select * from categoria", (rs, rowNum) -> new Categoria(rs.getInt("id_categoria")
																														, rs.getString("nombre")
																														, rs.getDate("ultima_actualizacion")));

		return listaCategoria;
	}
	
	
}
