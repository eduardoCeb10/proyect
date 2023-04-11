package org.ditalia.bbb.service.db;

import java.util.List;
import java.util.Optional;

import org.ditalia.bbb.entity.Accesorio;
import org.ditalia.bbb.repository.AccesoriosRepository;
import org.ditalia.bbb.service.IntServiceAccesorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary
public class AccesorioServiceJpa implements IntServiceAccesorio {

	@Autowired
	private AccesoriosRepository repoAccesorios;
	
	@Override
	public List<Accesorio> obtenerAccesorio() {
		// TODO Auto-generated method stub
		return repoAccesorios.findAll();
	}

	@Override
	public void guardar(Accesorio accesorio) {
		// TODO Auto-generated method stub
		repoAccesorios.save(accesorio);
	}

	@Override
	public Accesorio buscarPorId(Integer idAccesorio) {
		// TODO Auto-generated method stub
		Optional<Accesorio>optional =repoAccesorios.findById(idAccesorio);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(Integer idAccesorio) {
		// TODO Auto-generated method stub
		repoAccesorios.deleteById(idAccesorio);
	}

	@Override
	public long numAccesorios() {
		// TODO Auto-generated method stub
		return repoAccesorios.count();
	}

	@Override
	public Page<Accesorio> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoAccesorios.findAll(page);
	}

	@Override
	public List<Accesorio> buscarPorCategoria(Integer idCategoria) {
		// TODO Auto-generated method stub
		return repoAccesorios.buscarPorCategoria(idCategoria);
	}

	@Override
	public List<Accesorio> buscarPorColorYNombre(String palabraClave) {
		// TODO Auto-generated method stub
		return repoAccesorios.buscarPorColorYNombre(palabraClave);
	}

	@Override
	public List<Accesorio> obtenerAccesorio(String palabraClave, Integer idCategoria) {
		if(palabraClave != null) {
			return repoAccesorios.findAll(palabraClave, idCategoria);
		}
		return repoAccesorios.findAll();
	}

}
