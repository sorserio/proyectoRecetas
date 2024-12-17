package com.sabropedia.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sabropedia.models.Local;

@Repository
public interface RepositorioLocales extends CrudRepository<Local, Long> {

}