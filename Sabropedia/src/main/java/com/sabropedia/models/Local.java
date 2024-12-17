package com.sabropedia.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "local")
public class Local {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	@NotBlank(message = "Por favor proporciona el nombre de tu local")
	@Size(min = 5, message = "El titulo debe tener como minimo 5 caracteres" )
	private String nombreLocal;
	
	@NotBlank(message = "Por favor proporciona la descripcion de su local")
	@Size(min = 10, message = "La descripcion debe tener como minimo 10 caracteres" )
	private String descripcion;
	
	@NotBlank(message = "Por favor proporciona la categoria")
	@Size(min = 5, message = "La categoria debe tener como minimo 5 caracteres" )
	private String categoria;
	
	@ManyToOne
	@JoinColumn(name= "id_usuario")
	private UsuarioLocal creador;
	
	@ManyToMany(mappedBy = "local", cascade = CascadeType.ALL)
	private List<Plato> platos;
	
	@OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
	private List<Comentario> comentarios;

	public Local(Long id, String nombreLocal, String descripcion, String categoria, UsuarioLocal creador, List<Plato> platos, List<Comentario> comentarios) {
		super();
		this.id = id;
		this.nombreLocal = nombreLocal;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.creador = creador;
		this.platos = platos;
		this.comentarios = comentarios;
	}
	
	public Local() {
		this.id = 0l;
		this.nombreLocal = "";
		this.descripcion = "";
		this.categoria = "";
		this.creador = null;
		this.platos = null;
		this.comentarios = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreLocal() {
		return nombreLocal;
	}

	public void setNombreLocal(String nombreLocal) {
		this.nombreLocal = nombreLocal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public UsuarioLocal getCreador() {
		return creador;
	}

	public void setCreador(UsuarioLocal creador) {
		this.creador = creador;
	}

	public List<Plato> getPlatos() {
		return platos;
	}

	public void setPlatos(List<Plato> platos) {
		this.platos = platos;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	
	
	
	
}