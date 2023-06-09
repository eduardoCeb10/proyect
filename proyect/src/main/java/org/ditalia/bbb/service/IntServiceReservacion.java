package org.ditalia.bbb.service;

import java.util.List;
import org.ditalia.bbb.entity.Reservacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IntServiceReservacion {

	public List<Reservacion> obtenerReservacion();
	public void guardar(Reservacion reservacion);
	public Reservacion buscarPorId(Integer idReservacion);
	public void eliminar(Integer idReservacion);
	public long numReservacion();
	Page<Reservacion>buscarTodas(Pageable page);
	public List<Reservacion>obtenerReservacionesPorUsuario(String usuario);
	public Reservacion obtenerPdf(int idReservacion);
	public List<String>obtenerColores();
	public List<String>obtenerColoresAccesorio();
	public List<String>obtenerTallas();
	
}
