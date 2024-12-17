package com.sabropedia.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="comentarios")
public class Comentario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 10, max = 400, message = "Debe contener entre 10 y 400 caracteres.")
	private String mensaje;
	
	@Size(min = 1, max = 5, message = "La califacion debe estar entre 1 y 5.")
	private double calificacion;

	@ManyToOne
	@JoinColumn(name = "id_local")
	private Local local;
	
	@ManyToOne
	@JoinColumn(name= "id_usuario")
	private Usuario creador;

	public Comentario(Long id, String mensaje, double calificacion, Local local, Usuario creador) {
		super();
		this.id = id;
		this.mensaje = mensaje;
		this.calificacion = calificacion;
		this.local = local;
		this.creador = creador;
	}
	
	public Comentario() {
		super();
		this.id = 0l;
		this.mensaje = "";
		this.calificacion = 0;
		this.local = null;
		this.creador = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Usuario getCreador() {
		return creador;
	}

	public void setCreador(Usuario creador) {
		this.creador = creador;
	}
	
	
	
}
	
