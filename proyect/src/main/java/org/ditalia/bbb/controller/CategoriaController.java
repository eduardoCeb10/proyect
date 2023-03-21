package org.ditalia.bbb.controller;

import java.util.List;

import org.ditalia.bbb.entity.Categoria;
import org.ditalia.bbb.service.IntServiceCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/categoria")
@Controller
public class CategoriaController {

	@Autowired
	private IntServiceCategoria serviceCategoria;
	
	@GetMapping("/buscar")
	public String modCat(@RequestParam("id") int idCategoria, Model model) {
		Categoria categoria = serviceCategoria.buscarPorId(idCategoria);
		model.addAttribute("categoria", categoria);
		return "categoria/formCategoria";
	}
	
	@PostMapping("/guardar")
	public String guardar(Categoria categoria) {
		serviceCategoria.guardar(categoria);
		return "redirect:/categoria/index";
	}
	
	@GetMapping("/nueva")
	public String nuevaCate(Categoria categoria) {
		return "categoria/formCategoria";
	}
	
	@GetMapping("/eliminar")
	public String eliminarCate(@RequestParam("id") int idCategoria, RedirectAttributes model) {
		serviceCategoria.eliminar(idCategoria);
		model.addFlashAttribute("msg", "Categoria eliminada con exito");
		return "redirect:/categoria/index";
	}
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Categoria> categorias = serviceCategoria.obtenerCategoria();
		model.addAttribute("categorias", categorias);
		model.addAttribute("total", serviceCategoria.numCategorias());
		return "categoria/listaCategoria";
	}
}
