package org.iesvdm.controller;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.Valid;
import org.iesvdm.domain.Categoria;
import org.iesvdm.domain.Idioma;
import org.iesvdm.domain.Pelicula;
import org.iesvdm.dto.PeliculaDTO;
import org.iesvdm.service.PeliculaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PeliculaController {

	private PeliculaService peliculaService;

	public PeliculaController(PeliculaService peliculaService) {
		super();
		this.peliculaService = peliculaService;

	}

	@GetMapping("/peliculas")
	public String listarPeliculas(Model model) {

		int peliculasTotales = peliculaService.totalPeliculas();
		model.addAttribute( "totalPeliculas", peliculasTotales);

		BigDecimal costeReemplazoHorror = peliculaService.peliculasHorrorReplacement();
		model.addAttribute("costeReemplazoHorror", costeReemplazoHorror);

		String peliculasPorCategoria = peliculaService.obtenerPeliculasPorCategoria();
		model.addAttribute("peliculasPorCategoria", peliculasPorCategoria);
		
		//List<Pelicula> listPeliculas = this.peliculaService.all();
		List<Pelicula> listPeliculas = peliculaService.ListAll();

		model.addAttribute("peliculasDTO", listPeliculas);
		
		return "peliculas";
	}
	
	@GetMapping("/peliculas/crear")
	public String getCrearPelicula(Model model) {


		
		Pelicula pelicula = new Pelicula();
		model.addAttribute("pelicula", pelicula);

		List<Idioma> lstIdioma = peliculaService.listIdioma();
		model.addAttribute("listaIdiomas", lstIdioma);

		List<Categoria> lstCat = peliculaService.listCategoria();
		model.addAttribute("listaCategoria", lstCat);
		
		return "crear-pelicula";
	}

	@PostMapping("/peliculas/crear")
	public RedirectView postCrearPelicula(@ModelAttribute("pelicula") Pelicula pelicula) {

		peliculaService.create(pelicula);

			return new RedirectView("/pelicula");
	}
}
