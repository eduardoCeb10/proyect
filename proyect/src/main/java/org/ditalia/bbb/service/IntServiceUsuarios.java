package org.ditalia.bbb.service;

import java.util.List;
import org.ditalia.bbb.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IntServiceUsuarios {

	public List<Usuario> obtenerUsuarios();
	public void agregar(Usuario usuario);
	public Usuario buscarPorId(Integer idUsuario);
	public void eliminar(Integer idUsuario);
	public long totalUsuarios();
	//public Usuario buscarPorUsername(String username);
	Page<Usuario>buscarTodas(Pageable page);
}
