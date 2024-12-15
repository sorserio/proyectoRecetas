package com.sabropedia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sabropedia.models.Plato;
import com.sabropedia.repositories.RepositorioPlatos;

@Service
public class ServicioPlatos {

	@Autowired
	private RepositorioPlatos repositorio;

	public Plato crear(Plato plato) {
		return this.repositorio.save(plato);
	}

	public Plato obtenerPorId(Long id) {
		return this.repositorio.findById(id).orElse(null);
	}

	public List<Plato> obtenerTodosLosPlatos() {
		return (List<Plato>) this.repositorio.findAll();
	}

	public Plato actualizar(Plato plato) {
		return this.repositorio.save(plato);
	}

	public void eliminarPorId(Long id) {
		this.repositorio.deleteById(id);
	}
}