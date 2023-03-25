package org.ditalia.bbb.controller;

import java.util.LinkedList;
import java.util.List;
import org.ditalia.bbb.entity.Vestido;
import org.ditalia.bbb.service.IntServiceVestido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	@Autowired
	private IntServiceVestido serviceVestido;
	/*
	 @Autowired
	 private IntServiceUsuario serviceUsuario;
	 @Autowired
	private PasswordEncoder passwordEncoder;
	 */
	
	@GetMapping("/")
	public String mostrarIndex(Model model) {
		List<Vestido> aux = serviceVestido.obtenerVestido();
		List<Vestido>vestidos = new LinkedList<>();
		for(Vestido ve : aux) {
			if(ve.getDestacado()==1) {
				vestidos.add(ve);
			}
		}
		model.addAttribute("vestidos", vestidos);
		return "home";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "formRegistro";
	}
	
	/*@PostMapping("/guardar")
	public String guardar(Usuario usuario) {
		usuario.setEstatus(1);
		//usuario.setPassword("{noop}"+usuario.getPassword());
		String texto = usuario.getPassword();
		String encriptado = passwordEncoder.encode(texto);
		usuario.setPassword(encriptado);
		Perfil per = new Perfil();
		per.setId(3);
		usuario.agregar(per);
		serviceUsuario.agregar(usuario);
		System.out.println(usuario);
		return "home";
	}*/
	
	@GetMapping("/acerca")
	public String acerca() {
		return "acerca";
	}
	
	@GetMapping("/login")
	public String login() {
		return "formLogin";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/";
	}
}