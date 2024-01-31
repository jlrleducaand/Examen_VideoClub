package org.iesvdm.dao;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.domain.Idioma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Slf4j

@Repository
public class IdiomaDAOImpl implements IdiomaDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;



	@Override
	public List<Idioma> findAll() {
		List<Idioma> listIdioma =  this.jdbcTemplate.query("select * from idioma", (rs, rowNum) -> new Idioma(rs.getLong("id_idioma")
																					, rs.getString("nombre")
																					, rs.getDate("ultima_actualizacion")));
		return listIdioma;
	}


}
