package org.ditalia.bbb.service;

import java.util.LinkedList;
import java.util.List;

import org.ditalia.bbb.entity.Vestido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VestidoServiceImp implements IntServiceVestido {

	private List<Vestido>vestidos = null;
	
	public VestidoServiceImp() {
		vestidos = new LinkedList<>();
		Vestido v1 = new Vestido();
		v1.setFolio(453);
		v1.setModelo("Alisson");
		v1.setColor("Hueso");
		v1.setTalla("Mediano");
		v1.setPrecio(7.500);
		v1.setEstatus("Disponible");
		v1.setDescripcion("Vestido mediano");
		vestidos.add(v1);
	}
	@Override
	public List<Vestido> obtenerVestido() {
		// TODO Auto-generated method stub
		return vestidos;
	}

	@Override
	public void guardar(Vestido vestido) {
		// TODO Auto-generated method stub
		vestidos.add(vestido);
	}

	@Override
	public Vestido buscarPorId(Integer idVestido) {
		// TODO Auto-generated method stub
		for(Vestido ve : vestidos) {
			if(ve.getFolio().compareTo(idVestido)==0) {
				return ve;
			}
		}
		return null;
	}

	@Override
	public void eliminar(Integer idVestido) {
		// TODO Auto-generated method stub
		vestidos.remove(buscarPorId(idVestido));
	}

	@Override
	public long numVestidos() {
		// TODO Auto-generated method stub
		return vestidos.size();
	}

	@Override
	public Page<Vestido> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

}
