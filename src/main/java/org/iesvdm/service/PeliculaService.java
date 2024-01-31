package org.iesvdm.service;

import java.util.List;
import java.util.Set;

import org.iesvdm.domain.Categoria;
import org.iesvdm.domain.Idioma;
import org.iesvdm.domain.Pelicula;
import org.iesvdm.dto.PeliculaDTO;
import org.iesvdm.dao.CategoriaDAO;
import org.iesvdm.dao.IdiomaDAO;
import org.iesvdm.dao.PeliculaDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PeliculaService {

	@Value("${app.videoclub_examen.array.clasificaciones}")
	private String[] arrayClasificaciones;
	@Value("#{${app.videoclub_examen.set.clasificaciones}}")
	private Set<String> setCaracteristicasEspeciales;
	
	private PeliculaDAO peliculaDAO;
	private IdiomaDAO idiomaDAO;
	private CategoriaDAO categoriaDAO;
	
	public PeliculaService(PeliculaDAO peliculaDAO, IdiomaDAO idiomaDAO, CategoriaDAO categoriaDAO) {
		this.peliculaDAO = peliculaDAO;
		this.idiomaDAO = idiomaDAO;
		this.categoriaDAO = categoriaDAO;
	}
	
	public List<Pelicula> all() {
		return this.peliculaDAO.findAll();
	}
	
	public List<PeliculaDTO> allDTO() {
		return this.peliculaDAO.findAllDTO();
	}

	public void create(Pelicula pelicula) {
		peliculaDAO.create(pelicula);
	}
	
	public List<Categoria> getListaCategorias() {
		return this.categoriaDAO.findAll();
	}
	
	public List<Idioma> getListaIdiomas() {
		return this.idiomaDAO.findAll();
	}
	
	public String[] getArrayClasificaciones() {
		return arrayClasificaciones;
	}

	public Set<String> getSetCaracteristicasEspeciales() {
		return setCaracteristicasEspeciales;
	}
	
}
