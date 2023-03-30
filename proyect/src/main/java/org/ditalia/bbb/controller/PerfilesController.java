package org.ditalia.bbb.controller;

import org.ditalia.bbb.entity.Perfil;
import org.ditalia.bbb.service.IntServicePerfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/perfiles")
public class PerfilesController {
	
	@Autowired
	private IntServicePerfiles servicePerfil;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
	model.addAttribute("perfiles", servicePerfil.obtenerPerfiles());
	return "perfiles/listaPerfiles";
	}
	
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam("id")int idPerfil, RedirectAttributes model) {
		servicePerfil.eliminar(idPerfil);
		model.addFlashAttribute("msg", "Perfil eliminado");
		return "redirect:/perfiles/index";
	}
	
	@GetMapping("/bloquear")
	public String bloquearUsuario(@RequestParam ("id") int idPerfil, RedirectAttributes model) {
		Perfil perfil = servicePerfil.buscarPorId(idPerfil);
		model.addFlashAttribute("msg", perfil.getPerfil()+" bloqueado");
		return "redirect:/perfiles/index";
	}
	
	@GetMapping("/desbloquear")
	public String desbloquearUsuario(@RequestParam ("id") int idPerfil, RedirectAttributes model) {
		Perfil perfil = servicePerfil.buscarPorId(idPerfil);
		model.addFlashAttribute("msg", perfil.getPerfil()+" desbloqueado");
		return "redirect:/perfiles/index";
	}
}