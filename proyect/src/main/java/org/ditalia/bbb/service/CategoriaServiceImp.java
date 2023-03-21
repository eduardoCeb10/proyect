package org.ditalia.bbb.service;

import java.util.LinkedList;
import java.util.List;

import org.ditalia.bbb.entity.Categoria;
import org.springframework.stereotype.Service;
@Service
public class CategoriaServiceImp implements IntServiceCategoria {
	private List<Categoria> categorias = null;
	
	public CategoriaServiceImp() {
	categorias = new LinkedList<>();
	Categoria cat1 = new Categoria();
	cat1.setId(1);
	cat1.setNombre("Xv años");
	cat1.setDescripcion("Relacionado con las fiestas de xv años");
	
	categorias.add(cat1);
	}

	@Override
	public List<Categoria> obtenerCategoria() {
		// TODO Auto-generated method stub
		return categorias;
	}

	@Override
	public void guardar(Categoria categoria) {
		// TODO Auto-generated method stub
		categorias.add(categoria);
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		// TODO Auto-generated method stub
		for(Categoria c : categorias) {
			if(c.getId().compareTo(idCategoria)==0) {
				return c;
			}
		}
		return null;
	}

	@Override
	public void eliminar(Integer idCategoria) {
		// TODO Auto-generated method stub
		categorias.remove(buscarPorId(idCategoria));
	}

	@Override
	public int numCategorias() {
		// TODO Auto-generated method stub
		return categorias.size();
	}

}
