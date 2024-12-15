package com.sabropedia.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.sabropedia.models.LoginUsuario;
import com.sabropedia.models.Usuario;
import com.sabropedia.repositories.RepositorioUsuarios;

@Service
public class ServicioUsuarios {

	@Autowired
	private RepositorioUsuarios repositorio;

	public Usuario crear(Usuario usuario) {
		String hashPassword = BCrypt.hashpw(usuario.getContraseña(), BCrypt.gensalt());
		usuario.setContraseña(hashPassword);
		return this.repositorio.save(usuario);
	}

	public List<Usuario> obtenerTodosLosUsuarios() {
		return (List<Usuario>) this.repositorio.findAll();
	}

	public Usuario obtenerPorId(Long id) {
		return this.repositorio.findById(id).orElse(null);
	}

	public Usuario obtenerPorEmail(String email) {
		return this.repositorio.findByEmail(email).orElse(null);
	}

	public Usuario actualizar(Usuario usuario) {
		return this.repositorio.save(usuario);
	}

	public void eliminarPorId(Long id) {
		this.repositorio.deleteById(id);
	}

	public BindingResult validarRegistro(BindingResult validaciones, Usuario usuario) {
		if (!usuario.getContraseña().equals(usuario.getConfirmarContraseña())) {
			validaciones.rejectValue("confirmarContraseña", "contraseñaNoCoincide", "La contraseñas no coinciden.");
		}
		return validaciones;
	}

	public BindingResult validarLogin(BindingResult validaciones, LoginUsuario usuario) {
		Usuario usuarioDb = this.obtenerPorEmail(usuario.getCorreo());
		if (usuarioDb == null) {
			validaciones.rejectValue("correo", "correoNoRegistrado",
					"El correo electrónico ingresado no se encuentra en nuestra base de datos.");
		} else {
			if (!BCrypt.checkpw(usuario.getContraseña(), usuarioDb.getContraseña())) {
				validaciones.rejectValue("contraseña", "contraseñaIncorrecta", "Contraseña incorrecta.");
			}
		}
		return validaciones;
	}
}