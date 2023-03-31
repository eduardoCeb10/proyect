package org.ditalia.bbb.controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.ditalia.bbb.entity.Vestido;
import org.ditalia.bbb.service.IntServiceCategoria;
import org.ditalia.bbb.service.IntServiceVestido;
import org.ditalia.bbb.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vestidos")
public class VestidoController {

	@Autowired
	private IntServiceVestido serviceVestido;
	
	@Autowired
	private IntServiceCategoria serviceCategorias;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		model.addAttribute("vestidos", serviceVestido.obtenerVestido());
		model.addAttribute("total", serviceVestido.numVestidos());
		return "vestidos/listaVestidos";
	}
	
	@GetMapping(value = "/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Vestido> lista = serviceVestido.buscarTodas(page);
		/*List<Vestido>aux= new LinkedList<>();
		for(Vestido ve : lista) {
			if(ve.getModelo().equalsIgnoreCase("Sin vestido")) {
				break;
			} else {
				aux.add(ve);
			}
		}*/
		model.addAttribute("vestidos", lista);
		model.addAttribute("total", serviceVestido.numVestidos()-1);
		return "vestidos/listaVestidos";
	}
	
	/*@GetMapping("/eliminar")
	public String eliminarVes(@RequestParam("folio") int folioVestido, RedirectAttributes model) {
		serviceVestido.eliminar(folioVestido);
		model.addFlashAttribute("msg", "vestido eliminado corectamente");
		return "redirect:/vestidos/index";
	}*/
	
	@GetMapping("/nueva")
	public String nuevoVestido(Vestido vestido, Model model) {
		model.addAttribute("categorias", serviceCategorias.obtenerCategoria());
		return "vestidos/formVestido";
	}
	
	@PostMapping("/guardar")
	public String guardar(Vestido vestido,BindingResult result,@RequestParam("archivoImagen") MultipartFile multiPart, RedirectAttributes model) {
		if (result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
				}
			model.addAttribute("categorias", serviceCategorias.obtenerCategoria());
			return "vestidos/formVestido";
			}
		if (!multiPart.isEmpty()) {
			//String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			String ruta = "c:/vestidos/img-vestidos/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ 
				// La imagen si se subio
				// Procesamos la variable nombreImagen
				vestido.setImagen(nombreImagen);
				}
			}
		if(vestido.getFolio()==null) model.addFlashAttribute("msg", "Vestido Guardado");
		else model.addFlashAttribute("msg", "Vestido modificado");
		
		serviceVestido.guardar(vestido);
		return "redirect:/vestidos/indexPaginado";
		}
	
	@GetMapping("/buscar")
	public String modVestido(@RequestParam ("folio") int folioVestido, Model model) {
		Vestido vestido = serviceVestido.buscarPorId(folioVestido);
		model.addAttribute("categorias", serviceCategorias.obtenerCategoria());
		model.addAttribute("vestido", vestido);
		return "vestidos/formVestido";
	}
	
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam("folio") int idVestido, RedirectAttributes model) {
		serviceVestido.eliminar(idVestido);
		model.addFlashAttribute("msg", "Vestido eliminado");
		return "redirect:/vestidos/indexPaginado";
	}
	
	@GetMapping("/detalle")
	public String detalle(@RequestParam("folio")int idVestido, Model model) {
		Vestido vestido = serviceVestido.buscarPorId(idVestido);
		double aux = vestido.getPrecio()*0.16;
		double x = aux+vestido.getPrecio();
		model.addAttribute("iva", x);
		model.addAttribute("vestido", vestido);
		return "vestidos/detalle";
	}
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
        @Override
        public void setAsText(String text) throws IllegalArgumentException{
          setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }

        @Override
        public String getAsText() throws IllegalArgumentException {
          return DateTimeFormatter.ofPattern("dd-MM-yyyy").format((LocalDate) getValue());
        }  
    
      });
  }
}
