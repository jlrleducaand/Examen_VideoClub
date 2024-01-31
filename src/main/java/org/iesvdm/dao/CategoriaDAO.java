package org.iesvdm.dao;

import java.util.List;

import org.iesvdm.domain.Categoria;
import org.iesvdm.dto.CategoriaDTO;

public interface CategoriaDAO {

	public Categoria find(Long id);
	public CategoriaDTO findDTO(Long id);
	public List<Categoria> findAll();
}
