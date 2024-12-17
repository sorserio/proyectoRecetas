package com.sabropedia.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sabropedia.models.LoginUsuario;
import com.sabropedia.models.LoginUsuarioLocal;
import com.sabropedia.models.Usuario;
import com.sabropedia.models.UsuarioLocal;
import com.sabropedia.services.ServicioUsuarios;
import com.sabropedia.services.ServicioUsuariosLocal;

@Controller
public class ControladorRecuperar {

    @Autowired
    private ServicioUsuarios servicioUsuarios;

    @Autowired
    private ServicioUsuariosLocal servicioUsuariosLocal;

    @GetMapping("/recuperar")
    public String recuperaContrasena(Model modelo) {
        modelo.addAttribute("loginUsuario", new LoginUsuario());
        return "recuperar.jsp";
    }

    @GetMapping("/recuperarLocal")
    public String recuperaContrasenaLocal(Model modelo) {
        modelo.addAttribute("loginUsuarioLocal", new LoginUsuarioLocal());
        return "recuperarLocal.jsp";
    }

    @PostMapping("/procesa/recuperar")
    public String procesarRecuperacion(@ModelAttribute("loginUsuario") LoginUsuario loginUsuario, Model modelo) {
        Usuario usuario = servicioUsuarios.obtenerPorEmail(loginUsuario.getCorreo());
        if (usuario == null) {
            modelo.addAttribute("error", "El correo no está registrado.");
            return "recuperar.jsp";
        }

        if (loginUsuario.getContraseña() == null || loginUsuario.getContraseña().isEmpty()) {
            modelo.addAttribute("error", "Debe ingresar una nueva contraseña.");
            return "recuperar.jsp";
        }

        String hashedPassword = BCrypt.hashpw(loginUsuario.getContraseña(), BCrypt.gensalt());
        usuario.setContraseña(hashedPassword);
        servicioUsuarios.actualizar(usuario);

        modelo.addAttribute("mensaje", "Contraseña actualizada correctamente.");
        return "redirect:/login";
    }

    @PostMapping("/procesa/recuperarLocal")
    public String procesarRecuperacionLocal(@ModelAttribute("loginUsuarioLocal") LoginUsuarioLocal loginUsuarioLocal, Model modelo) {
        UsuarioLocal usuarioLocal = servicioUsuariosLocal.obtenerPorEmail(loginUsuarioLocal.getCorreo());
        if (usuarioLocal == null) {
            modelo.addAttribute("error", "El correo no está registrado.");
            return "recuperarLocal.jsp";
        }

        if (loginUsuarioLocal.getContraseña() == null || loginUsuarioLocal.getContraseña().isEmpty()) {
            modelo.addAttribute("error", "Debe ingresar una nueva contraseña.");
            return "recuperarLocal.jsp";
        }

        String hashedPassword = BCrypt.hashpw(loginUsuarioLocal.getContraseña(), BCrypt.gensalt());
        usuarioLocal.setContraseña(hashedPassword);
        servicioUsuariosLocal.actualizar(usuarioLocal);

        modelo.addAttribute("mensaje", "Contraseña actualizada correctamente.");
        return "redirect:/loginLocal";
    }
}
