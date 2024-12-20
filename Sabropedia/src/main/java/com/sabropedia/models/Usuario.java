package com.sabropedia.models;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name= "usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Por favor proporciona tu nombre") 
	@Size(min = 3, message = "El nombre debe contener al menos 3 caracteres.")
	private String nombre;
	
	@NotBlank(message = "Por favor proporciona tu apellido") 
	@Size(min = 3, message = "El apellido debe contener al menos 3 caracteres.")
	private String apellido;
	
	@Column(unique = true)
	@NotBlank(message = "El campo es requerido")
	@Email(message="Por favor ingresa un correo electronico valido")
	private String correo;
	
	@NotBlank(message = "El campo es requerido")
	@Size(min= 8, message = "Debe contener al menos 8 caracteres")
	private String contraseña;
	
	@Transient
	private String confirmarContraseña;
	
	@OneToMany(mappedBy = "creador")
	private List<Comentario> comentarios;

	public Usuario(Long id, String nombre, String apellido, String correo, String contraseña, String confirmarContraseña, List<Comentario> comentarios) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.contraseña = contraseña;
		this.confirmarContraseña = confirmarContraseña;
		this.comentarios = comentarios;
	}
	
	public Usuario() {
		super();
		this.id = 0l;
		this.nombre = "";
		this.apellido = "";
		this.correo = "";
		this.contraseña = "";
		this.confirmarContraseña = "";
		this.comentarios = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getConfirmarContraseña() {
		return confirmarContraseña;
	}

	public void setConfirmarContraseña(String confirmarContraseña) {
		this.confirmarContraseña = confirmarContraseña;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	
	
	
}