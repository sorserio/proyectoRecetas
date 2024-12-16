package com.sabropedia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.sabropedia.models.Comentario;
import com.sabropedia.models.Local;
import com.sabropedia.services.ServicioComentarios;
import com.sabropedia.services.ServicioLocales;
import com.sabropedia.services.ServicioUsuariosLocal;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorLocales {
	
	@Autowired
	private ServicioLocales servicioLocales;
	
	@Autowired
	private ServicioUsuariosLocal servicioUsuariosLocal;
	
	@Autowired
	private ServicioComentarios servicioComentarios;
	
	@GetMapping("/inicio")
	private String mostrarLocales(HttpSession sesion, Model modelo) {
		Long idUsuario = (Long) sesion.getAttribute("idUsuario");
		if(sesion.getAttribute("nombreCompleto") == null) {
			return "redirect:/";
		}
		modelo.addAttribute("locales", this.servicioLocales.obtenerTodosLosLocales());
		modelo.addAttribute("localesManejados", this.servicioUsuariosLocal.obtenerPorId(idUsuario).getLocal());
		return "articulos.jsp";
	}
	
	@GetMapping("/form/agregar")
	public String agregar(HttpSession sesion, Model modelo) {
		if (sesion.getAttribute("nombreCompleto") == null) {
			return "redirect:/";
		}
		modelo.addAttribute("local", new Local());
		return "agregar.jsp";
	}
	
	@GetMapping("/form/editar/{id}")
	public String editar(@PathVariable("id") Long id, HttpSession sesion, Model modelo, @ModelAttribute("local") Local local) {
		if (sesion.getAttribute("nombreCompleto") == null) {
			return "redirect:/";
		}
		modelo.addAttribute("local", this.servicioLocales.obtenerPorId(id));
		return "editar.jsp";
	}
	
	@PostMapping("/guardar")
	public String guardar(HttpSession sesion, @Valid @ModelAttribute("local") Local local, BindingResult validaciones) {
		if (sesion.getAttribute("nombreCompleto") == null) {
			return "redirect:/";
		}
		if(validaciones.hasErrors()) {
			return "agregar.jsp";
		}
		this.servicioLocales.crear(local);
		return "redirect:/inicio";
		
	}
	
	@PutMapping("/actualizar/{id}")
	public String actualizar(@PathVariable("id") Long id, HttpSession sesion, @Valid @ModelAttribute("local") Local local, BindingResult validaciones ) {
		if (sesion.getAttribute("nombreCompleto") == null) {
			return "redirect:/";
		}
		if(validaciones.hasErrors()) {
			return "editar.jsp";
		}
		this.servicioLocales.actualizar(local);
		return "redirect:/articulos";
		
		
	}
	
	@DeleteMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long id, HttpSession sesion) {
		if (sesion.getAttribute("nombreCompleto") == null) {
			return "redirect:/";
		}
		this.servicioLocales.eliminarPorId(id);
		return "redirect:/articulos";
		
	}
	
	@GetMapping("/detalle/{id}")
	public String detalle(@PathVariable("id") Long id, HttpSession sesion, Model modelo) {
		if (sesion.getAttribute("nombreCompleto") == null) {
			return "redirect:/";
		}
		modelo.addAttribute("local", this.servicioLocales.obtenerPorId(id));
		return "detalle.jsp";
		
	}
	
	@GetMapping("/form/comentar/{id}")
	public String comentar(@PathVariable("id") Long id, HttpSession sesion, Model modelo) {
		if (sesion.getAttribute("nombreCompleto") == null) {
			return "redirect:/";
		}
		modelo.addAttribute("local", this.servicioLocales.obtenerPorId(id));
		modelo.addAttribute("comentario", new Comentario());
		return "comentar.jsp";
	}
		
		
	@PostMapping("/comentar")
	public String comentar(HttpSession sesion, @Valid @ModelAttribute("comentario") Comentario comentario, BindingResult validaciones) {
		if (sesion.getAttribute("nombreCompleto") == null) {
			return "redirect:/";
		}
		if(validaciones.hasErrors()) {
			return "comentar.jsp";
		}
		servicioComentarios.crear(comentario);
		return "redirect:/inicio";
	}
	
}
