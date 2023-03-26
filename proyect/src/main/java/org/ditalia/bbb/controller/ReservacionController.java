package org.ditalia.bbb.controller;

import org.ditalia.bbb.entity.Reservacion;
import org.ditalia.bbb.service.IntServiceAccesorio;
import org.ditalia.bbb.service.IntServiceCategoria;
import org.ditalia.bbb.service.IntServiceReservacion;
import org.ditalia.bbb.service.IntServiceVestido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reservaciones")
public class ReservacionController {

	@Autowired
	private IntServiceReservacion serviceReservacion;
	@Autowired
	private IntServiceCategoria serviceCategoria;
	@Autowired
	private IntServiceAccesorio serviceAccesorio;
	@Autowired
	private IntServiceVestido serviceVestidos;
	
	@GetMapping(value="/indexPaginado")
	public String mostrarIndexPaginado(Model model) {
		model.addAttribute("reservaciones", serviceReservacion.obtenerReservacion());
		model.addAttribute("total", serviceReservacion.numReservacion());
		return "reservaciones/listaReservaciones";
	}
	
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam("id") int idReservacion, RedirectAttributes model) {
		serviceReservacion.eliminar(idReservacion);
		model.addFlashAttribute("msg", "Reservacion eliminada");
		return "redirect:/reservaciones/indexPaginado";
	}
	
	@GetMapping("/nueva")
	public String nuevaReservacion(Reservacion reservacion, Model model) {
		model.addAttribute("categorias", serviceCategoria.obtenerCategoria());
		model.addAttribute("accesorios", serviceAccesorio.obtenerAccesorio());
		model.addAttribute("vestidos", serviceVestidos.obtenerVestido());
		return "reservaciones/formReservacion";
	}
	
	@PostMapping("/guardar")
	public String guardar(Reservacion reservacion,BindingResult result, RedirectAttributes model) {
		if(reservacion.getId()==null) model.addFlashAttribute("msg", "Reservación agregada");
		else model.addFlashAttribute("msg", "Reservación modificada");
		serviceReservacion.guardar(reservacion);
		return "redirect:/reservaciones/indexPaginado";
	}
	
	@GetMapping("/buscar")
	public String buscar(@RequestParam("id")int idReservacion, Model model) {
		Reservacion reservacion = serviceReservacion.buscarPorId(idReservacion);
		model.addAttribute("categorias", serviceCategoria.obtenerCategoria());
		model.addAttribute("vestidos", serviceVestidos.obtenerVestido());
		model.addAttribute("accesorios", serviceAccesorio.obtenerAccesorio());
		model.addAttribute("reservaciones", serviceReservacion.obtenerReservacion());
		model.addAttribute("reservacion", reservacion);
		return "reservaciones/formReservacion";
	}
}
