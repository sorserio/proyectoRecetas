package com.sabropedia.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sabropedia.models.Comentario;

@Repository
public interface RepositorioComentarios extends CrudRepository<Comentario, Long>{

}
