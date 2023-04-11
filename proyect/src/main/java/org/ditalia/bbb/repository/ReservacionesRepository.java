package org.ditalia.bbb.repository;

import java.util.List;

import org.ditalia.bbb.entity.Reservacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservacionesRepository extends JpaRepository<Reservacion, Integer> {

	@Query(value="SELECT * FROM reservaciones r WHERE r.username = :username", nativeQuery=true)
	List<Reservacion> buscarPorUsername(@Param("username")String username);
	
	@Query(value="SELECT * FROM reservaciones r WHERE r.id = :id", nativeQuery=true)
	Reservacion buscarReservacion(@Param("id")int id);
	
	@Query(value="SELECT DISTINCT color from vestidos", nativeQuery=true)
	List<String> colores();
	
	@Query(value="SELECT DISTINCT color from accesorios", nativeQuery=true)
	List<String>coloresA();
	
	@Query(value="SELECT DISTINCT talla from vestidos", nativeQuery=true)
	List<String>tallas();
	
}
