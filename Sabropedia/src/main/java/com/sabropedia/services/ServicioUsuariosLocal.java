package com.sabropedia.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import com.sabropedia.models.LoginUsuarioLocal;
import com.sabropedia.models.UsuarioLocal;
import com.sabropedia.repositories.RepositorioUsuariosLocal;

public class ServicioUsuariosLocal {

	@Autowired
	private RepositorioUsuariosLocal repositorio;
	
	public UsuarioLocal crear (UsuarioLocal usuarioLocal) { //
		String hashPassword = BCrypt.hashpw(usuarioLocal.getContraseña(), BCrypt.gensalt());
		usuarioLocal.setContraseña(hashPassword);
		return this.repositorio.save(usuarioLocal);
	}

	public List<UsuarioLocal> obtenerTodosLosUsuariosLocal() {
		return (List<UsuarioLocal>) this.repositorio.findAll();
	}

	public UsuarioLocal obtenerPorId(Long id) {
		return this.repositorio.findById(id).orElse(null);
	}

	public UsuarioLocal obtenerPorEmail(String email) {
		return this.repositorio.findByEmail(email).orElse(null);
	}

	public UsuarioLocal actualizar(UsuarioLocal usuarioLocal) {
		return this.repositorio.save(usuarioLocal);
	}

	public void eliminarPorId(Long id) {
		this.repositorio.deleteById(id);
	}

	public BindingResult validarRegistro(BindingResult validaciones, UsuarioLocal usuarioLocal) {
		if (!usuarioLocal.getContraseña().equals(usuarioLocal.getConfirmarContraseña())) {
			validaciones.rejectValue("confirmarContraseña", "contraseñaNoCoincide", "La contraseñas no coinciden.");
		}
		return validaciones;
	}

	public BindingResult validarLogin(BindingResult validaciones, LoginUsuarioLocal usuarioLocal) {
		UsuarioLocal usuarioLocalDb = this.obtenerPorEmail(usuarioLocal.getCorreo());
		if (usuarioLocalDb == null) {
			validaciones.rejectValue("correo", "correoNoRegistrado",
					"El correo electrónico ingresado no se encuentra en nuestra base de datos.");
		} else {
			if (!BCrypt.checkpw(usuarioLocal.getContraseña(), usuarioLocalDb.getContraseña())) {
				validaciones.rejectValue("contraseña", "contraseñaIncorrecta", "Contraseña incorrecta.");
			}
		}
		return validaciones;
	}
}

