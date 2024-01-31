package org.iesvdm.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.iesvdm.domain.Pelicula;
import org.iesvdm.dto.PeliculaDTO;
import org.iesvdm.service.PeliculaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PeliculaController {

	private PeliculaService peliculaService;

	public PeliculaController(PeliculaService peliculaService) {
		super();
		this.peliculaService = peliculaService;

	}

	@GetMapping("/peliculas")
	public String listarPeliculas(Model model) {
		
		//List<Pelicula> listPeliculas = this.peliculaService.all();
		List<PeliculaDTO> listPeliculasDTO = this.peliculaService.allDTO();

		model.addAttribute("peliculasDTO", listPeliculasDTO);
		
		return "peliculas";
	}
	
	@GetMapping("/peliculas/crear")
	public String getCrearPelicula(@ModelAttribute Pelicula pelicula, Model model) {
		
		model.addAttribute("listaIdiomas", this.peliculaService.getListaIdiomas());
		model.addAttribute("listaCategorias", this.peliculaService.getListaCategorias());
		model.addAttribute("arrayClasificaciones", this.peliculaService.getArrayClasificaciones());
		
		return "crear-pelicula";
	}

	@PostMapping("/peliculas/crear")
	public String postCrearPelicula(@ModelAttribute @Valid Pelicula pelicula, BindingResult result) {
		if (result.hasErrors()) {
			// Manejar errores de validación
			return "crear-pelicula";
		}

		this.peliculaService.create(pelicula);

		return "redirect:/peliculas";
	}
	
}
