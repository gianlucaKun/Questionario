package questionario.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import questionario.project.dto.QuizDomandaDTO;
import questionario.project.dto.proiezione.QuizProiezione;
import questionario.project.service.QuizDomandaService;
import questionario.project.service.QuizService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/quiz/domanda")
public class QuizDomandaController {
	
	@Autowired
	QuizDomandaService s;
	
	@Autowired
	QuizService qs;
	
	@GetMapping("/all")
	public List<QuizDomandaDTO> getAll() {
		return s.selectAll();
	}
	
	@GetMapping("/find")
	public QuizDomandaDTO select(@RequestParam("id") Long id) {
		return s.selectById(id);
	}
	
	@PostMapping("/add")
	public void add(@RequestBody QuizDomandaDTO q) {
		s.add(q);
	}
	
	@PutMapping("/update")
	public QuizDomandaDTO update(@RequestBody QuizDomandaDTO q, @RequestParam("id") Long id) {
		return s.update(q, id);
	}
	
	@DeleteMapping("delete")
	public void delete(@RequestParam("id") Long id) {
		s.delete(id);
	}
	
	//TEST
	   @GetMapping("/{quizId}")
	    public ResponseEntity<QuizProiezione> getQuiz(@PathVariable Long quizId) {
	        QuizProiezione quizProiezione = s.getQuizWithQuestionsAndAnswers(quizId);
	        if (quizProiezione != null) {
	            return ResponseEntity.ok(quizProiezione);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

}
