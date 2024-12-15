package com.sabropedia.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginUsuario {

	@NotBlank(message = "El campo es requerido.")
	@Email(message = "Por favor ingresa un correo electrónico válido.")
	private String correo;

	@NotBlank(message = "El campo es requerido.")
	@Size(min = 8, message = "Debe contener al menos 8 caracteres.")
	private String contraseña;

	public LoginUsuario() {
		super();
		this.correo = "";
		this.contraseña = "";
	}

	public LoginUsuario(String correo, String contraseña) {
		super();
		this.correo = correo;
		this.contraseña = contraseña;
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

}