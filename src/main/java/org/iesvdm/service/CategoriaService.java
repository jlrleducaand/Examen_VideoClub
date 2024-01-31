package org.iesvdm.service;

import org.iesvdm.dao.CategoriaDAOImpl;
import org.iesvdm.domain.Categoria;
import org.iesvdm.dto.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CategoriaService {

	CategoriaDTO categoriaDTO;
	CategoriaDAOImpl categoriaDAOImpl;

	@Autowired
	public CategoriaService(CategoriaDTO categoriaDTO,CategoriaDAOImpl  categoriaDAOImpl) {
		this.categoriaDTO = categoriaDTO;
		this.categoriaDAOImpl = categoriaDAOImpl;
	}

	public Categoria one(Long id) {
		return categoriaDAOImpl.find(id);
	}
	
	public CategoriaDTO oneDTO(Long id) {
		return categoriaDAOImpl.findDTO(id);
	}

}
