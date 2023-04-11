package org.ditalia.bbb.controller;

import java.util.List;
import org.ditalia.bbb.entity.Reservacion;
import org.ditalia.bbb.service.IntServiceAccesorio;
import org.ditalia.bbb.service.IntServiceCategoria;
import org.ditalia.bbb.service.IntServiceReservacion;
import org.ditalia.bbb.service.IntServiceUsuarios;
import org.ditalia.bbb.service.IntServiceVestido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	@Autowired
	private IntServiceUsuarios serviceUsuario;
	
	/*BUSCA LAS RESERVACIONES PARA EL ROL ADMINISTRADOR*/
	@GetMapping(value="/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Reservacion>lista = serviceReservacion.buscarTodas(page);
		model.addAttribute("reservaciones", lista);
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
		model.addAttribute("vestidos", serviceVestidos.obtenetTodosVestidos());
		model.addAttribute("usuarios", serviceUsuario.obtenerUsuarios());
		model.addAttribute("colores", serviceReservacion.obtenerColores());
		model.addAttribute("coloresA", serviceReservacion.obtenerColoresAccesorio());
		model.addAttribute("tallas", serviceReservacion.obtenerTallas());

		return "reservaciones/formReservacion";
	}
	
	@PostMapping("/guardar")
	public String guardar(Reservacion reservacion, BindingResult result, RedirectAttributes model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		reservacion.setUsername(username);
		
		/*if(result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
				}
				model.addAttribute("categorias", serviceCategoria.obtenerCategoria());
				return "redirect:/reservaciones/formReservacion";
		}
		
		LocalTime horaReservacion = reservacion.getHora();
		if(horaReservacion.isBefore(LocalTime.parse("10:00:00")) || horaReservacion.isAfter(LocalTime.parse("18:30:00"))) {
			result.rejectValue("hora", "hora.invalida", "La hora debe estar entre las 10:00:00 y las 18:30:00.");
			return "reservaciones/formReservacion";
		}*/
		
		if(reservacion.getId()==null) model.addFlashAttribute("msg", "¡Tu reservación fue realizada con éxito!");
		else model.addFlashAttribute("msg", "Reservación modificada");
		serviceReservacion.guardar(reservacion);
		return "redirect:/";
	}
	
	@GetMapping("/buscar")
	public String buscar(@RequestParam("id")int idReservacion, Model model) {
		Reservacion reservacion = serviceReservacion.buscarPorId(idReservacion);
		model.addAttribute("categorias", serviceCategoria.obtenerCategoria());
		model.addAttribute("vestidos", serviceVestidos.obtenetTodosVestidos());
		model.addAttribute("accesorios", serviceAccesorio.obtenerAccesorio());
		model.addAttribute("reservaciones", serviceReservacion.obtenerReservacion());
		model.addAttribute("reservacion", reservacion);
		model.addAttribute("usuarios", serviceUsuario.obtenerUsuarios());
		model.addAttribute("colores", serviceReservacion.obtenerColores());
		model.addAttribute("coloresA", serviceReservacion.obtenerColoresAccesorio());
		model.addAttribute("tallas", serviceReservacion.obtenerTallas());

		return "reservaciones/formReservacion";
	}
	
	/*BUSCA LAS RESERVACIONES PARA EL ROL USUARIO*/

	@GetMapping("/misReservaciones")
	public String reservacionUsuario(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		List<Reservacion> reservaciones = serviceReservacion.obtenerReservacionesPorUsuario(username);
		model.addAttribute("reservaciones", reservaciones);
		return "reservaciones/reservacionUsuario";
	}
	
	@GetMapping("/elimina")
	public String elimina(@RequestParam("id") int idReservacion, RedirectAttributes model) {
		serviceReservacion.eliminar(idReservacion);
		model.addFlashAttribute("msg", "Reservacion eliminada");
		return "redirect:/reservaciones/misReservaciones";
	}
	
	@GetMapping("/busca")
	public String busca(@RequestParam("id")int idReservacion, Model model) {
		Reservacion reservacion = serviceReservacion.buscarPorId(idReservacion);
		model.addAttribute("categorias", serviceCategoria.obtenerCategoria());
		model.addAttribute("vestidos", serviceVestidos.obtenetTodosVestidos());
		model.addAttribute("accesorios", serviceAccesorio.obtenerAccesorio());
		model.addAttribute("reservaciones", serviceReservacion.obtenerReservacion());
		model.addAttribute("reservacion", reservacion);
		model.addAttribute("usuarios", serviceUsuario.obtenerUsuarios());
		model.addAttribute("colores", serviceReservacion.obtenerColores());
		model.addAttribute("coloresA", serviceReservacion.obtenerColoresAccesorio());
		model.addAttribute("tallas", serviceReservacion.obtenerTallas());
		return "reservaciones/reservacionUsuario";
	}
}
