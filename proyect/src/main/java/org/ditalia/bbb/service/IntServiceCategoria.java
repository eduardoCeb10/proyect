package org.ditalia.bbb.service;

import java.util.List;

import org.ditalia.bbb.entity.Categoria;

public interface IntServiceCategoria {

	public List<Categoria>obtenerCategoria();
	public void guardar (Categoria categoria);
	public Categoria buscarPorId(Integer idCategoria);
	public void eliminar (Integer idCategoria);
	public int numCategorias();
}
