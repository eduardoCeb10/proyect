package org.ditalia.bbb.repository;

import java.util.List;

import org.ditalia.bbb.entity.Vestido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VestidosRepository extends JpaRepository<Vestido, Integer> {

	@Query(value="SELECT * FROM vestidos v WHERE CONCAT(v.color, v.modelo) LIKE %?% AND v.idCategoria = ?", nativeQuery=true)
	public List<Vestido> findAll(String palabraClave, Integer idCategoria);
	
	@Query(value="SELECT * from vestidos WHERE idCategoria = ?", nativeQuery=true)
	public List<Vestido> buscarPorCategoria(Integer idCategoria);
	
	@Query(value="SELECT * from vestidos v WHERE CONCAT(v.color, v.modelo) LIKE %?%", nativeQuery=true)
	public List<Vestido> buscarPorColorYModelo (String palabraClave);
	
	/*@Query(value="SELECT * FROM vestidos v WHERE v.idCategoria = ?", nativeQuery=true)
	public List<Vestido> buscarPorCategorias(Integer idCategoria);*/
}
