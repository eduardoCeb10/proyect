package org.ditalia.bbb.service.db;

import java.util.List;
import java.util.Optional;

import org.ditalia.bbb.entity.Vestido;
import org.ditalia.bbb.repository.VestidosRepository;
import org.ditalia.bbb.service.IntServiceVestido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary
public class VestidoServiceJpa implements IntServiceVestido {

	@Autowired
	private VestidosRepository repoVestidos;
	
	@Override
	public List<Vestido> obtenerVestido(String palabraClave, Integer idCategoria) {
		if(palabraClave != null) {
			return repoVestidos.findAll(palabraClave, idCategoria);
		}
		return repoVestidos.findAll();
	}

	@Override
	public void guardar(Vestido vestido) {
		// TODO Auto-generated method stub
		repoVestidos.save(vestido);
	}

	@Override
	public Vestido buscarPorId(Integer idVestido) {
		// TODO Auto-generated method stub
		Optional <Vestido> optional=repoVestidos.findById(idVestido);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(Integer idVestido) {
		// TODO Auto-generated method stub
		repoVestidos.deleteById(idVestido);
	}

	@Override
	public long numVestidos() {
		// TODO Auto-generated method stub
		return repoVestidos.count();
	}

	@Override
	public Page<Vestido> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoVestidos.findAll(page);
	}

	@Override
	public List<Vestido> obtenetTodosVestidos() {
		// TODO Auto-generated method stub
		return repoVestidos.findAll();
	}

	@Override
	public List<Vestido> buscarPorCategoria(Integer idCategoria) {
		// TODO Auto-generated method stub
		return repoVestidos.buscarPorCategoria(idCategoria);
	}

	@Override
	public List<Vestido> buscarPorColorYModelo(String palabraClave) {
		// TODO Auto-generated method stub
		if(palabraClave != null) {
			return repoVestidos.buscarPorColorYModelo(palabraClave);
		}
		return repoVestidos.findAll();
	}

	/*@Override
	public List<Vestido> buscarPorCategoria(Integer idCategoria) {
		// TODO Auto-generated method stub
		if(idCategoria != null) {
			return repoVestidos.buscarPorCategorias(idCategoria);
		}
		return repoVestidos.findAll();
		
	}*/
}
