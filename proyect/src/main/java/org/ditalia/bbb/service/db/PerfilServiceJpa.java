package org.ditalia.bbb.service.db;

import java.util.List;
import java.util.Optional;

import org.ditalia.bbb.entity.Perfil;
import org.ditalia.bbb.repository.PerfilesRepository;
import org.ditalia.bbb.service.IntServicePerfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PerfilServiceJpa implements IntServicePerfiles {

	@Autowired
	private PerfilesRepository repoPerfiles;
	
	@Override
	public List<Perfil> obtenerPerfiles() {
		// TODO Auto-generated method stub
		return repoPerfiles.findAll();
	}

	@Override
	public void agregar(Perfil perfil) {
		// TODO Auto-generated method stub
		repoPerfiles.save(perfil);
	}

	@Override
	public Perfil buscarPorId(Integer idPerfil) {
		// TODO Auto-generated method stub
		Optional<Perfil>optional = repoPerfiles.findById(idPerfil);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(Integer idPerfil) {
		// TODO Auto-generated method stub
		repoPerfiles.deleteById(idPerfil);
	}

	@Override
	public long totalPerfiles() {
		// TODO Auto-generated method stub
		return repoPerfiles.count();
	}

	@Override
	public Page<Perfil> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoPerfiles.findAll(page);
	}

}
