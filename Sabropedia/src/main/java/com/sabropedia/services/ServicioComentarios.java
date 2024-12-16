package com.sabropedia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sabropedia.models.Comentario;
import com.sabropedia.repositories.RepositorioComentarios;

@Service
public class ServicioComentarios {
	
	@Autowired
	private RepositorioComentarios repositorioComentarios;
	
	public Comentario crear(Comentario comentario) {
		return this.repositorioComentarios.save(comentario);
	}
	
	public List<Comentario> obtenerTodos(){
		return (List<Comentario>)this.repositorioComentarios.findAll();
	}
	
	public Comentario actualizar (Comentario comentario) {
		return this.repositorioComentarios.save(comentario);
	}
	
	public void eliminarPorId(Long id) {
		this.repositorioComentarios.deleteById(id);
	}
}
