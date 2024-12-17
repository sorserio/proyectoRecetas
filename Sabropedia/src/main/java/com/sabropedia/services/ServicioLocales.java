package com.sabropedia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sabropedia.models.Local;
import com.sabropedia.repositories.RepositorioLocales;

@Service
public class ServicioLocales {

	@Autowired
	private RepositorioLocales repositorio;

	public Local crear(Local local) {
		return this.repositorio.save(local);
	}

	public Local obtenerPorId(Long id) {
		return this.repositorio.findById(id).orElse(null);
	}

	public List<Local> obtenerTodosLosLocales() {
		return (List<Local>) this.repositorio.findAll();
	}

	public Local actualizar(Local local) {
		return this.repositorio.save(local);
	}

	public void eliminarPorId(Long id) {
		this.repositorio.deleteById(id);
	}
}