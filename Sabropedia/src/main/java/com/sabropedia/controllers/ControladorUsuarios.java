package com.sabropedia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sabropedia.models.LoginUsuario;
import com.sabropedia.models.LoginUsuarioLocal;
import com.sabropedia.models.Usuario;
import com.sabropedia.models.UsuarioLocal;
import com.sabropedia.services.ServicioUsuarios;
import com.sabropedia.services.ServicioUsuariosLocal;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorUsuarios {
	
	@Autowired
	private ServicioUsuarios servicioUsuarios;
	
	@Autowired
	private ServicioUsuariosLocal servicioUsuariosLocal;
	
	@GetMapping("/")
	public String eligeRegistro(){
		return "escoge.jsp";
	}
	
	@GetMapping("/registro")
	public String formRegistro(Model modelo) {
		modelo.addAttribute("usuario", new Usuario());
		return "registro.jsp";
	}
	
	@GetMapping("/login")
	public String formLogin(Model modelo){
		modelo.addAttribute("loginUsuario", new LoginUsuario());
		return "login.jsp";
	}
	
	@GetMapping("/registroLocal")
	public String formRegistroLocal(Model modelo) {
		modelo.addAttribute("usuarioLocal", new UsuarioLocal());
		return "registro.jsp";
	}
	
	@GetMapping("/loginLocal")
	public String formLoginLocal(Model modelo){
		modelo.addAttribute("loginUsuarioLocal", new LoginUsuarioLocal());
		return "login.jsp";
	}
	
	@GetMapping("/logout")
	public String logOut(HttpSession sesion) {
		sesion.invalidate();
		return "redirect:/";
		
	}
	
	@PostMapping("/procesa/registro")
	public String registro(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult validaciones, Model modelo,
			HttpSession sesion){
		this.servicioUsuarios.validarRegistro(validaciones, usuario);
		if (validaciones.hasErrors()) {
			return "registro.jsp";
		}
		Usuario usuario2 = this.servicioUsuarios.crear(usuario);
		sesion.setAttribute("nombreCompleto", usuario2.getNombre() + " " + usuario2.getApellido());
		sesion.setAttribute("idUsuario", usuario2.getId());
		return "redirect:/inicio";
		
	}
	
	@PostMapping("/procesa/login")
	public String login(@Valid @ModelAttribute("loginUsuario") LoginUsuario loginUsuario, BindingResult validaciones, Model modelo,
			HttpSession sesion) {
		this.servicioUsuarios.validarLogin(validaciones, loginUsuario);
		if (validaciones.hasErrors()) {
			return "login.jsp";
		}
		Usuario usuario = this.servicioUsuarios.obtenerPorEmail(loginUsuario.getCorreo());
		sesion.setAttribute("nombreCompleto", usuario.getNombre() + " " + usuario.getApellido());
		sesion.setAttribute("idUsuario", usuario.getId());
		return "redirect:/inicio";
	}
	
	@PostMapping("/procesa/registroLocal")
	public String registro(@Valid @ModelAttribute("usuarioLocal") UsuarioLocal usuarioLocal, BindingResult validaciones, Model modelo, HttpSession sesion){
		this.servicioUsuariosLocal.validarRegistro(validaciones, usuarioLocal);
		if (validaciones.hasErrors()) {
			return "registro.jsp";
		}
		UsuarioLocal usuarioLocal2 = this.servicioUsuariosLocal.crear(usuarioLocal);
		sesion.setAttribute("nombreCompleto", usuarioLocal2.getNombre() + " " + usuarioLocal2.getApellido());
		sesion.setAttribute("idUsuario", usuarioLocal2.getId());
		return "redirect:/inicioLocal";
		
	}
	
	@PostMapping("/procesa/loginLocal")
	public String login(@Valid @ModelAttribute("loginUsuario") LoginUsuarioLocal loginUsuarioLocal, BindingResult validaciones, Model modelo, HttpSession sesion) {
		this.servicioUsuariosLocal.validarLogin(validaciones, loginUsuarioLocal);
		if (validaciones.hasErrors()) {
			return "login.jsp";
		}
		UsuarioLocal usuarioLocal = this.servicioUsuariosLocal.obtenerPorEmail(loginUsuarioLocal.getCorreo());
		sesion.setAttribute("nombreCompleto", usuarioLocal.getNombre() + " " + usuarioLocal.getApellido());
		sesion.setAttribute("idUsuario", usuarioLocal.getId());
		return "redirect:/inicioLocal";
	}

}
