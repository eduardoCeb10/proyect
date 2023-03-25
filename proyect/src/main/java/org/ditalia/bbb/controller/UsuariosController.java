package org.ditalia.bbb.controller;

import org.ditalia.bbb.service.IntServiceUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	 @Autowired
	 private IntServiceUsuarios serviceUsuarios;
	 
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		model.addAttribute("usuarios", serviceUsuarios.obtenerUsuarios());
		return "usuarios/listaUsuarios";
	}
}