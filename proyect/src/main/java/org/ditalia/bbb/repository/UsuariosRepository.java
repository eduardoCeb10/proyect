package org.ditalia.bbb.repository;

import org.ditalia.bbb.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
	
	//public Usuario findByUsername(String username);

	//@Query(value = "select * from railway.usuarios where username like ?", nativeQuery = true)
	//public Usuario findByUsername(String username);

}
