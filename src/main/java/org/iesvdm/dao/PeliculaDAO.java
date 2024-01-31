package org.iesvdm.dao;

import java.util.List;

import org.iesvdm.domain.Pelicula;
import org.iesvdm.dto.PeliculaDTO;

public interface PeliculaDAO {

	public List<Pelicula> findAll();
	
	public List<PeliculaDTO> findAllDTO();
	
	public void create(Pelicula pelicula);

}
