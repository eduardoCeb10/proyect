package org.ditalia.bbb.service;

import java.util.List;
import org.ditalia.bbb.entity.Perfil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IntServicePerfiles {

	public List<Perfil> obtenerPerfiles();
	public void agregar(Perfil perfil);
	public Perfil buscarPorId(Integer idPerfil);
	public void eliminar(Integer idPerfil);
	public long totalPerfiles();
	Page<Perfil>buscarTodas(Pageable page);
}
