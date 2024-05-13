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

import questionario.project.dto.UtenteQuizDTO;
import questionario.project.service.UtenteQuizService;

@RestController
@RequestMapping("/utente/quiz")
@CrossOrigin(origins = "http://localhost:5173/")
public class UtenteQuizController {
	
	@Autowired
	UtenteQuizService s;
	
	@GetMapping("/all")
	public List<UtenteQuizDTO> getAll() {
		return s.selectAll();
	}
	
	@GetMapping("/find")
	public UtenteQuizDTO select(@RequestParam("id") Long id) {
		return s.selectByID(id);
	}
	
	@PostMapping("/add")
	public String add(@RequestBody UtenteQuizDTO q) {
		s.add(q);
		return s.getIdCreated(q.getUtenteId()).toString();
	}
	
	@PutMapping("/update")
	public UtenteQuizDTO update(@RequestBody UtenteQuizDTO q, @RequestParam("id") Long id) {
		return s.update(q, id);
	}
	
	@DeleteMapping("/admin/delete")
	public void delete(@RequestParam Long id) {
		s.delete(id);
	}

}
