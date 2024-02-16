package org.iesvdm.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.iesvdm.domain.Categoria;
import org.iesvdm.domain.Idioma;
import org.iesvdm.domain.Pelicula;
import org.iesvdm.dto.PeliculaDTO;
import org.iesvdm.dao.CategoriaDAO;
import org.iesvdm.dao.IdiomaDAO;
import org.iesvdm.dao.PeliculaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PeliculaService {

	/*@Value("${app.videoclub_examen.array.idiomas}")
	private String[] arrayIdiomas;

	@Value("#{${app.videoclub_examen.set.categorias}}")
	private Set<String> setCategoria;*/

	private PeliculaDAO peliculaDAO;
	private IdiomaDAO idiomaDAO;
	private CategoriaDAO categoriaDAO;
	private PeliculaDTO peliculaDTO;

	@Autowired
	public PeliculaService(PeliculaDAO peliculaDAO, IdiomaDAO idiomaDAO, CategoriaDAO categoriaDAO, PeliculaDTO peliculaDTO) {
		this.peliculaDAO = peliculaDAO;
		this.idiomaDAO = idiomaDAO;
		this.categoriaDAO = categoriaDAO;
		this.peliculaDTO = peliculaDTO;
	}

	public List<Categoria> listCategoria() {
		return categoriaDAO.findAll();
	}

	public List<Idioma> listIdioma(){
		return idiomaDAO.findAll();
	}

	public List<Pelicula> ListAll() {
		return peliculaDAO.findAll();
	}

	public List<PeliculaDTO> PeliculasDeTerror() {
		return peliculaDAO.findAllHorrorDTO();
	}

	public void create(Pelicula pelicula) {
		peliculaDAO.create(pelicula);
	}

	public List<Categoria> getListaCategorias() {
		return this.categoriaDAO.findAll();
	}


	//Aqui van las funciones especiales

	public BigDecimal peliculasHorrorReplacement(){
		BigDecimal res = BigDecimal.ZERO;

		List<PeliculaDTO> pelisHorror = peliculaDAO.findAllHorrorDTO();
		for (PeliculaDTO  peli : pelisHorror) {
			res = res.add(peli.getReplacement_cost());
		}

		return res;
	}

	public int totalPeliculas(){
		return ListAll().size();
	}

	public String  obtenerPeliculasPorCategoria(){
		Map<String, Integer> pelisPorCate = peliculaDAO.findPelisPorCategoria();

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Categoría\tCantidad de Películas\n");

		for (Map.Entry<String, Integer> entry : pelisPorCate.entrySet()) {
			stringBuilder.append(entry.getKey()).append("\t\t").append(entry.getValue()).append("\n");
		}

		String resultado = stringBuilder.toString();

		return resultado;
	}


}