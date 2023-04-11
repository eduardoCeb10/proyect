package org.ditalia.bbb.service;

import java.util.List;

import org.ditalia.bbb.entity.Accesorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IntServiceAccesorio {

	public List<Accesorio>obtenerAccesorio();
	public List<Accesorio>buscarPorCategoria(Integer idCategoria);
	public List<Accesorio>buscarPorColorYNombre(String palabraClave);
	public List<Accesorio>obtenerAccesorio(String palabraClave, Integer idCategoria);
	public void guardar (Accesorio accesorio);
	public Accesorio buscarPorId(Integer idAccesorio);
	public void eliminar (Integer idAccesorio);
	public long numAccesorios();
	Page<Accesorio>buscarTodas(Pageable page);
}
