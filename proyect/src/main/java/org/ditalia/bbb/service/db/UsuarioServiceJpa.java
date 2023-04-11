package org.ditalia.bbb.service.db;

import java.util.List;
import java.util.Optional;
import org.ditalia.bbb.entity.Usuario;
import org.ditalia.bbb.repository.UsuariosRepository;
import org.ditalia.bbb.service.IntServiceUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UsuarioServiceJpa implements IntServiceUsuarios {

	@Autowired 
	private UsuariosRepository repoUsuario;
	@Override
	public List<Usuario> obtenerUsuarios() {
		// TODO Auto-generated method stub
		return repoUsuario.findAll();
	}

	@Override
	public void agregar(Usuario usuario) {
		// TODO Auto-generated method stub
		repoUsuario.save(usuario);
	}

	@Override
	public Usuario buscarPorId(Integer idUsuario) {
		// TODO Auto-generated method stub
		Optional<Usuario>optional = repoUsuario.findById(idUsuario);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(Integer idUsuario) {
		// TODO Auto-generated method stub
		repoUsuario.deleteById(idUsuario);
	}

	@Override
	public long totalUsuarios() {
		// TODO Auto-generated method stub
		return repoUsuario.count();
	}

	@Override
	public Page<Usuario> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoUsuario.findAll(page);
	}

	/*@Override
	public Usuario buscarPorUsername(String username) {
		return repoUsuario.findByUsername(username);
	}*/

}
