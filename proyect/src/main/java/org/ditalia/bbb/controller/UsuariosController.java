package org.ditalia.bbb.controller;

import org.ditalia.bbb.entity.Usuario;
import org.ditalia.bbb.service.IntServiceUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	 @Autowired
	 private IntServiceUsuarios serviceUsuarios;
	 
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		model.addAttribute("usuarios", serviceUsuarios.obtenerUsuarios());
		model.addAttribute("total", serviceUsuarios.obtenerUsuarios().size());
		return "usuarios/listaUsuarios";
	}
	
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam("id") int idUsuario, RedirectAttributes model) {
		serviceUsuarios.eliminar(idUsuario);
		model.addFlashAttribute("msg", "Usuario eliminado");
		return "redirect:/usuarios/index";
	}
	
	@GetMapping("/bloquear")
	public String bloquearUsuario(@RequestParam ("id") int idUsuario, Model model) {
		Usuario usuario = serviceUsuarios.buscarPorId(idUsuario);
		usuario.setEstatus(0);
		serviceUsuarios.agregar(usuario);
		return "redirect:/usuarios/index";
	}
	
	@GetMapping("/desbloquear")
	public String desbloquearUsuario(@RequestParam ("id") int idUsuario, Model model) {
		Usuario usuario = serviceUsuarios.buscarPorId(idUsuario);
		usuario.setEstatus(1);
		serviceUsuarios.agregar(usuario);
		return "redirect:/usuarios/index";
	}
}