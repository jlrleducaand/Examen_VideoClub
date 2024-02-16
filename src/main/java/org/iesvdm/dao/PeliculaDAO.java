package org.iesvdm.dao;

import java.util.List;
import java.util.Map;

import org.iesvdm.domain.Pelicula;
import org.iesvdm.dto.PeliculaDTO;

public interface PeliculaDAO {

	public List<Pelicula> findAll();


	public List<PeliculaDTO> findAllHorrorDTO();

	public Map<String, Integer> findPelisPorCategoria();
	
	public void create(Pelicula pelicula);

}
