package org.ditalia.bbb.service.db;

import java.util.List;
import java.util.Optional;

import org.ditalia.bbb.entity.Categoria;
import org.ditalia.bbb.repository.CategoriasRepository;
import org.ditalia.bbb.service.IntServiceCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class CategoriaServiceJpa implements IntServiceCategoria {

	@Autowired
	private CategoriasRepository repoCategorias;
	
	@Override
	public List<Categoria> obtenerCategoria() {
		return (List<Categoria>) repoCategorias.findAll();
	}

	@Override
	public void guardar(Categoria categoria) {
		// TODO Auto-generated method stub
		repoCategorias.save(categoria);
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		// TODO Auto-generated method stub
		Optional<Categoria>optional=repoCategorias.findById(idCategoria);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(Integer idCategoria) {
		// TODO Auto-generated method stub
		repoCategorias.deleteById(idCategoria);
	}

	@Override
	public int numCategorias() {
		// TODO Auto-generated method stub
		return (int) repoCategorias.count();
	}

}
