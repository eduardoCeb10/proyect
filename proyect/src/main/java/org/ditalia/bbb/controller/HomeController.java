package org.ditalia.bbb.controller;

import java.util.LinkedList;
import java.util.List;

import org.ditalia.bbb.entity.Accesorio;
import org.ditalia.bbb.entity.Perfil;
import org.ditalia.bbb.entity.Usuario;
import org.ditalia.bbb.entity.Vestido;
import org.ditalia.bbb.service.IntServiceAccesorio;
import org.ditalia.bbb.service.IntServiceCategoria;
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
	private IntServiceCategoria serviceCategoria;
	 @Autowired
	private PasswordEncoder passwordEncoder;
	 
	@PostMapping("/buscar")
	public String buscarVestidos(Model model, String palabraClave, Integer idCategoria) {
		String titulo="", titulo2="";
		List<Vestido>todos = serviceVestido.obtenetTodosVestidos();
		List<Vestido>vestidos = new LinkedList<>();
		for(Vestido ve : todos) {	if(ve.getDestacado()==1) vestidos.add(ve); }
		List<Accesorio> acc= serviceAccesorio.obtenerAccesorio();
		List<Accesorio> accesorios = new LinkedList<>();
		for(Accesorio ac : acc) {	if(ac.getDestacado()==1) accesorios.add(ac);	}
		
		if(palabraClave.equals("") && idCategoria  != null) {
			//esta condicion es para buscar por categoria sino se proporciona una palabra clave
			model.addAttribute("vestidos",serviceVestido.buscarPorCategoria(idCategoria));
			model.addAttribute("accesorios", serviceAccesorio.buscarPorCategoria(idCategoria));
			titulo = "Vestidos por Categoria";
			titulo2= "Accesorios por Categoria";
		}else if(!palabraClave.equals("") && idCategoria == null){
			//buscar por palabra clave
			model.addAttribute("vestidos", serviceVestido.buscarPorColorYModelo(palabraClave));
			model.addAttribute("accesorios", serviceAccesorio.buscarPorColorYNombre(palabraClave));
			titulo = "Vestidos por color o modelo";
			titulo2 = "Accesorios por nombre";
		} else if(palabraClave.equals("") && idCategoria == null) {
			model.addAttribute("vestidos", vestidos);
			titulo = "Vestidos destacados";
			model.addAttribute("accesorios", accesorios);
			titulo2 = "Accesorios Destacados";
			return "redirect:/";
		} else {
			titulo = "Vestidos";
			model.addAttribute("vestidos", serviceVestido.obtenerVestido(palabraClave, idCategoria));
			titulo2 = "Accesorios";
			model.addAttribute("accesorios", serviceAccesorio.obtenerAccesorio(palabraClave, idCategoria));
		}
		
		model.addAttribute("categorias", serviceCategoria.obtenerCategoria());
		model.addAttribute("titulo", titulo);
		model.addAttribute("titulo2", titulo2);
		return "home";
	}
	 
	 
	@GetMapping("/")
	public String mostrarIndex(Model model) {
		List<Vestido>todos = serviceVestido.obtenetTodosVestidos();
		List<Vestido>vestidos = new LinkedList<>();
		
		for(Vestido ve : todos) {	if(ve.getDestacado()==1) vestidos.add(ve); }
		List<Accesorio> acc= serviceAccesorio.obtenerAccesorio();
		List<Accesorio> accesorios = new LinkedList<>();
		for(Accesorio ac : acc) {	if(ac.getDestacado()==1) accesorios.add(ac);	}
		
		model.addAttribute("categorias", serviceCategoria.obtenerCategoria());
		model.addAttribute("titulo", "Vestidos Destacados");
		model.addAttribute("vestidos", vestidos);
		model.addAttribute("accesorios", accesorios);
		
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
		return "redirect:/";
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