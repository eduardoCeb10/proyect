package org.ditalia.bbb.service;

import java.util.List;

import org.ditalia.bbb.entity.Vestido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IntServiceVestido {

	public List<Vestido>obtenerVestido(String palabraClave, Integer idCategoria);
	public List<Vestido>obtenetTodosVestidos();
	public List<Vestido>buscarPorCategoria(Integer idCategoria);
	public List<Vestido>buscarPorColorYModelo(String palabraClave);
	public void guardar (Vestido vestido);
	public Vestido buscarPorId(Integer idVestido);
	public void eliminar (Integer idVestido);
	public long numVestidos();
	Page<Vestido>buscarTodas(Pageable page);
}
