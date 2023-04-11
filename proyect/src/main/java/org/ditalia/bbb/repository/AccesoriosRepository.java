package org.ditalia.bbb.repository;

import java.util.List;

import org.ditalia.bbb.entity.Accesorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccesoriosRepository extends JpaRepository<Accesorio, Integer> {

	@Query(value="SELECT * FROM accesorios a WHERE CONCAT(a.color, a.nombre) LIKE %?% AND a.idCategoria = ?", nativeQuery=true)
	public List<Accesorio> findAll(String palabraClave, Integer idCategoria);
	
	@Query(value="SELECT * from accesorios WHERE idCategoria = ?", nativeQuery=true)
	public List<Accesorio> buscarPorCategoria(Integer idCategoria);
	
	@Query(value="SELECT * from accesorios WHERE CONCAT(color, nombre) LIKE %?%", nativeQuery=true)
	public List<Accesorio> buscarPorColorYNombre (String palabraClave);
}
