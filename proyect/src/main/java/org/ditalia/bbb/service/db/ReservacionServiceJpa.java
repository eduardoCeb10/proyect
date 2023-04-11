package org.ditalia.bbb.service.db;

import java.util.List;
import java.util.Optional;
import org.ditalia.bbb.entity.Reservacion;
import org.ditalia.bbb.repository.ReservacionesRepository;
import org.ditalia.bbb.service.IntServiceReservacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReservacionServiceJpa implements IntServiceReservacion {

	@Autowired
	private ReservacionesRepository repoReservacion;
	
	@Override
	public List<Reservacion> obtenerReservacion() {
		// TODO Auto-generated method stub
		return repoReservacion.findAll();
	}

	@Override
	public void guardar(Reservacion reservacion) {
		// TODO Auto-generated method stub
		repoReservacion.save(reservacion);
	}

	@Override
	public Reservacion buscarPorId(Integer idReservacion) {
		// TODO Auto-generated method stub
		Optional<Reservacion>optional=repoReservacion.findById(idReservacion);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(Integer idReservacion) {
		// TODO Auto-generated method stub
		repoReservacion.deleteById(idReservacion);
	}

	@Override
	public long numReservacion() {
		// TODO Auto-generated method stub
		return repoReservacion.count();
	}

	@Override
	public Page<Reservacion> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoReservacion.findAll(page);
	}

	@Override
	public List<Reservacion> obtenerReservacionesPorUsuario(String usuario) {
		// TODO Auto-generated method stub
		return repoReservacion.buscarPorUsername(usuario);
	}

	@Override
	public Reservacion obtenerPdf(int idReservacion) {
		// TODO Auto-generated method stub
		return repoReservacion.buscarReservacion(idReservacion);
	}

	@Override
	public List<String> obtenerColores() {
		// TODO Auto-generated method stub
		return repoReservacion.colores();
	}

	@Override
	public List<String> obtenerColoresAccesorio() {
		// TODO Auto-generated method stub
		return repoReservacion.coloresA();
	}

	@Override
	public List<String> obtenerTallas() {
		// TODO Auto-generated method stub
		return repoReservacion.tallas();
	}


}
