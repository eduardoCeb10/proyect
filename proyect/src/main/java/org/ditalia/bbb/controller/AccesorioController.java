package org.ditalia.bbb.controller;

import org.ditalia.bbb.entity.Accesorio;
import org.ditalia.bbb.service.IntServiceAccesorio;
import org.ditalia.bbb.service.IntServiceCategoria;
import org.ditalia.bbb.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/accesorios")
public class AccesorioController {

	@Autowired
	private IntServiceAccesorio serviceAccesorio;
	@Autowired
	private IntServiceCategoria serviceCategorias;
	
	@GetMapping("/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Accesorio> lista = serviceAccesorio.buscarTodas(page);
		/*List<Accesorio> aux= new LinkedList<>();
		for(Accesorio ac : lista) {
			if(ac.getNombre().equalsIgnoreCase("Sin accesorio")) {
				break;
			}else {
				aux.add(ac);
			}
		}*/
		model.addAttribute("accesorios", lista);
		model.addAttribute("total", serviceAccesorio.numAccesorios()-1);
		return "accesorios/listaAccesorios";
	}
	
	@GetMapping("/nueva")
	public String nuevoAccesorio(Accesorio accesorio, Model model) {
		model.addAttribute("categorias", serviceCategorias.obtenerCategoria());
		return "accesorios/formAccesorio";
	}
	
	@PostMapping("/guardar")
	public String guardar(Accesorio accesorio,BindingResult result,@RequestParam("archivoImagen") MultipartFile multiPart, RedirectAttributes model) {
		if (result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
				}
			model.addAttribute("categorias", serviceCategorias.obtenerCategoria());
			return "accesorios/formAccesorio";
			}
		if (!multiPart.isEmpty()) {
			//String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			String ruta = "c:/vestidos/img-vestidos/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ 
				// La imagen si se subio
				// Procesamos la variable nombreImagen
				accesorio.setImagen(nombreImagen);
				}
			}
		if(accesorio.getId()==null) model.addFlashAttribute("msg", "Accesorio Guardado");
		else model.addFlashAttribute("msg", "Accesorio modificado");
		
		serviceAccesorio.guardar(accesorio);
		return "redirect:/accesorios/indexPaginado";
		}
	@GetMapping("/buscar")
	public String modAccesorio(@RequestParam ("id") int idAccesorio, Model model) {
		Accesorio accesorio = serviceAccesorio.buscarPorId(idAccesorio);
		model.addAttribute("categorias", serviceCategorias.obtenerCategoria());
		model.addAttribute("accesorio", accesorio);
		return "accesorios/formAccesorio";
	}
	
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam("id") int idAccesorio, RedirectAttributes model) {
		serviceAccesorio.eliminar(idAccesorio);
		model.addFlashAttribute("msg", "Accesorio eliminado");
		return "redirect:/accesorios/indexPaginado";
	}
	
	@GetMapping("/detalle")
	public String detalle(@RequestParam("id")int idAccesorio, Model model) {
		Accesorio accesorio= serviceAccesorio.buscarPorId(idAccesorio);
		double aux = accesorio.getPrecio()*0.16;
		double x = aux+accesorio.getPrecio();
		model.addAttribute("iva", x);
		model.addAttribute("accesorio", accesorio);
		return "accesorios/detalle";
	}
}