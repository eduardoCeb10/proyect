package org.ditalia.bbb.controller;

import java.util.LinkedList;
import java.util.List;

import org.ditalia.bbb.entity.Vestido;
import org.ditalia.bbb.service.IntServiceVestido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@Autowired
	private IntServiceVestido serviceVestido;
	
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
}