package com.sabropedia.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="plato")
public class Plato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Por favor proporciona el nombre del plato")
	@Size(min = 5, message = "El nombre debe tener como minimo 5 caracteres" )
	private String nombrePlato;
	
	@NotBlank(message = "Por favor proporciona la descripcion de su plato")
	@Size(min = 10, message = "La descripcion debe tener como minimo 10 caracteres" )
	private String descripcion;

	@ManyToOne
	@JoinColumn(name = "id_local")
	private Local local;
	
	@ManyToOne
	@JoinColumn(name= "id_usuario")
	private UsuarioLocal creador;
	
	public Plato(Long id, String nombrePlato, String descripcion, Local local, UsuarioLocal creador) {
		super();
		this.id = id;
		this.nombrePlato = nombrePlato;
		this.descripcion = descripcion;
		this.local = local;
		this.creador = creador;
	}
	
	public Plato() {
		super();
		this.id = 0l;
		this.nombrePlato = "";
		this.descripcion = "";
		this.local = null;
		this.creador = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombrePlato() {
		return nombrePlato;
	}

	public void setNombrePlato(String nombrePlato) {
		this.nombrePlato = nombrePlato;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public UsuarioLocal getCreador() {
		return creador;
	}

	public void setCreador(UsuarioLocal creador) {
		this.creador = creador;
	}
	
	
	
}
