package com.sabropedia.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.sabropedia.models.UsuarioLocal;

@Repository
public interface RepositorioUsuariosLocal extends CrudRepository<UsuarioLocal, Long>{
	Optional<UsuarioLocal> findByEmail(String email);
}
