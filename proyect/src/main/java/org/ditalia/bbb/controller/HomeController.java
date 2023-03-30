package org.ditalia.bbb.controller;

import java.util.LinkedList;
import java.util.List;

import org.ditalia.bbb.entity.Perfil;
import org.ditalia.bbb.entity.Usuario;
import org.ditalia.bbb.entity.Vestido;
import org.ditalia.bbb.service.IntServiceAccesorio;
import org.ditalia.bbb.service.IntServiceUsuarios;
import org.ditalia.bbb.service.IntServiceVestido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	@Autowired
	private IntServiceVestido serviceVestido;
	@Autowired
	private IntServiceUsuarios serviceUsuario;
	@Autowired
	private IntServiceAccesorio serviceAccesorio;
	 @Autowired
	private PasswordEncoder passwordEncoder;
	 
	
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
		model.addAttribute("accesorios", serviceAccesorio.obtenerAccesorio());
		return "home";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "formRegistro";
	}
	
	@PostMapping("/guardar")
	public String guardar(Usuario usuario) {
		usuario.setEstatus(1);
		String texto = usuario.getPassword();
		String encriptado = passwordEncoder.encode(texto);
		usuario.setPassword(encriptado);
		Perfil per = new Perfil();
		per.setId(2);
		usuario.agregar(per);
		serviceUsuario.agregar(usuario);
		return "home";
	}
	
	@GetMapping("/acerca")
	public String acerca() {
		return "acerca";
	}
	
	@GetMapping("/contacto")
	public String contacto() {
		return "contacto";
	}
	
	@GetMapping("/mision")
	public String mision() {
		return "mision";
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