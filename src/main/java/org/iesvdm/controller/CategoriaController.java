package org.iesvdm.controller;

import org.iesvdm.dto.CategoriaDTO;
import org.iesvdm.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	


	@GetMapping("/detalle-categoria/{id}")
	public String verDetalleCategoria(@PathVariable Long id, Model model) {
		
		CategoriaDTO categoriaDTO = categoriaService.oneDTO(id);
		model.addAttribute("categoriaDTO", categoriaDTO);

		return "detalle-categoria";
	}
	
}
