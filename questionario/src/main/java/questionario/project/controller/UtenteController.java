package questionario.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import questionario.project.dto.UtenteDTO;
import questionario.project.service.UtenteService;

@RestController
@RequestMapping("/utente")
public class UtenteController {
	
	@Autowired
	UtenteService us;
	
	@GetMapping("/all")
	public List<UtenteDTO> getAll() {
		return us.selectAll();
	}
	
	@GetMapping("/find")
	public UtenteDTO select(@RequestParam("id") Long id) {
		return us.selectById(id);
	}
	
	@PostMapping("/add")
	public void add(@RequestBody UtenteDTO u) {
		us.add(u);
	}
	
	@PutMapping("/update")
	public UtenteDTO update(@RequestBody UtenteDTO u, @RequestParam("id") Long  id) {
		return us.update(u, id);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestParam("id") Long id) {
		us.delete(id);
	}
	
}
