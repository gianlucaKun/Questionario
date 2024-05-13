package questionario.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import questionario.project.dto.UtenteQuizDomandaRispostaDTO;
import questionario.project.service.UtenteQuizDomandaRispostaService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/utente/quiz/domanda/risposta")
@CrossOrigin(origins = "http://localhost:5173/")
public class UtenteQuizDomandaRispostaController {
	
	@Autowired
	UtenteQuizDomandaRispostaService s;
	
	@GetMapping("/all")
	public List<UtenteQuizDomandaRispostaDTO> getAll() {
		return s.seletcAll();
	}
	
	@GetMapping("/find")
	public UtenteQuizDomandaRispostaDTO select(@RequestParam("id") Long id) {
		return s.selectById(id);
	}
	
	@PostMapping("/add")
	public Long add(@RequestBody UtenteQuizDomandaRispostaDTO u) {
		s.add(u);
		return s.getIdCreated(u.getRispostaId(), u.getUtenteQuizDomandaId());
	}
	
	@PutMapping("/update")
	public UtenteQuizDomandaRispostaDTO update(@RequestBody UtenteQuizDomandaRispostaDTO u, @RequestParam("id") Long id) {
		return s.update(u, id);
	}
	
	@DeleteMapping("/admin/delete")
	public void update(@RequestParam("id") Long id) {
		s.delete(id);
	}
	
}
