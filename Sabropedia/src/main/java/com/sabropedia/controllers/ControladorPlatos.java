package com.sabropedia.controllers;

import java.util.List;

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

import com.sabropedia.models.Plato;
import com.sabropedia.services.ServicioPlatos;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorPlatos {

    @Autowired
    private ServicioPlatos servicio;

    @GetMapping("/platos")
    public String mostrarPlatos(HttpSession sesion, Model modelo) {
        Long idUsuario = (Long) sesion.getAttribute("idUsuario");
        if (idUsuario == null) {
            return "redirect:/";
        }
        List<Plato> platos = this.servicio.obtenerTodosLosPlatos();
        modelo.addAttribute("platos", platos);
        return "platos.jsp";
    }

    @GetMapping("/form/agregar")
    public String agregar(HttpSession sesion, Model modelo) {
        if (sesion.getAttribute("nombreCompleto") == null) {
            return "redirect:/";
        }
        modelo.addAttribute("plato", new Plato());
        return "agregar.jsp";
    }

    @GetMapping("/form/editar/{id}")
    public String editar(@PathVariable("id") Long id, HttpSession sesion, Model modelo,
            @ModelAttribute("plato") Plato plato) {
        if (sesion.getAttribute("nombreCompleto") == null) {
            return "redirect:/";
        }
        modelo.addAttribute("plato", this.servicio.obtenerPorId(id));
        return "editar.jsp";
    }

    @PostMapping("/guardar")
    public String guardar(HttpSession sesion, @Valid @ModelAttribute("plato") Plato plato, BindingResult validaciones) {
        if (sesion.getAttribute("nombreCompleto") == null) {
            return "redirect:/";
        }
        if (validaciones.hasErrors()) {
            return "agregar.jsp";
        }
        this.servicio.crear(plato);
        return "redirect:/platos";
    }

    @PutMapping("/actualizar/{id}")
    public String actualizar(@PathVariable("id") Long id, HttpSession sesion,
            @Valid @ModelAttribute("plato") Plato plato, BindingResult validaciones) {
        if (sesion.getAttribute("nombreCompleto") == null) {
            return "redirect:/";
        }
        if (validaciones.hasErrors()) {
            return "editar.jsp";
        }
        this.servicio.actualizar(plato);
        return "redirect:/platos";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id, HttpSession sesion) {
        if (sesion.getAttribute("nombreCompleto") == null) {
            return "redirect:/";
        }
        this.servicio.eliminarPorId(id);
        return "redirect:/platos";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable("id") Long id, HttpSession sesion, Model modelo) {
        if (sesion.getAttribute("nombreCompleto") == null) {
            return "redirect:/";
        }
        modelo.addAttribute("plato", this.servicio.obtenerPorId(id));
        return "detalle.jsp";
    }
}
