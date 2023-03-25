package org.ditalia.bbb.repository;

import org.ditalia.bbb.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
