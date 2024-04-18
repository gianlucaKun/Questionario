package questionario.project.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import questionario.project.dto.UtenteDTO;
import questionario.project.service.UtenteService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/utente")
public class UtenteRestController {
	
	@Autowired
	UtenteService us;
	
	@GetMapping("/all")
	public List<UtenteDTO> getAll() {
		return us.getAll();
	}
	
	@GetMapping("/findByID")
	public UtenteDTO finbyid(@RequestParam("id") Long id) {
		return us.finByID(id);
	}
	
	@PostMapping("/add")
	public void add(@RequestBody UtenteDTO u) {
		us.add(u);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestParam("id") Long id) {
		us.delete(id);
	}

}
