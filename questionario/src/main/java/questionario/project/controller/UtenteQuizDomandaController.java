package questionario.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import questionario.project.dto.UtenteQuizDomandaDTO;
import questionario.project.service.UtenteQuizDomandaService;

@RestController
@RequestMapping("/utente/quiz/domanda")
@CrossOrigin(origins = "http://localhost:4200")
public class UtenteQuizDomandaController {
	
	@Autowired
	UtenteQuizDomandaService s;
	
	@GetMapping("/all")
	public List<UtenteQuizDomandaDTO> getAll() {
		return s.selectAll();
	}
	
	@GetMapping("/find")
	public UtenteQuizDomandaDTO seletc(@RequestParam("id") Long id) {
		return s.selectById(id);
	}
	
	@PostMapping("/add")
	public void add(@RequestBody UtenteQuizDomandaDTO u) {
		s.add(u);
	}
	
	@PutMapping("/update")
	public UtenteQuizDomandaDTO update(@RequestParam("id") Long id, @RequestBody UtenteQuizDomandaDTO u) {
		return s.update(id, u);
	}
	
	@DeleteMapping("delete")
	public void delete(@RequestParam("id") Long id) {
		s.delte(id);
	}
}
