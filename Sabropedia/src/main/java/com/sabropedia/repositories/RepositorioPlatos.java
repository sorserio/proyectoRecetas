package com.sabropedia.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sabropedia.models.Plato;

@Repository
public interface RepositorioPlatos extends CrudRepository<Plato, Long> {

}