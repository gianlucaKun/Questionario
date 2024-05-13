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

import questionario.project.dto.QuizDTO;
import questionario.project.dto.proiezione.QuizProiezione;
import questionario.project.service.QuizService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class QuizController {
	
	@Autowired
	QuizService qs;
	
	@GetMapping("/quiz/all")
	public List<QuizDTO> getAll(){
		return qs.selectAll();
	}
	
	@GetMapping("/api/quiz/find")
	public QuizDTO getMethodName(@RequestParam("id") Long id) {
		return qs.selectById(id);
	}
	
	@PostMapping("/api/quiz/add")
	public void add(@RequestBody QuizDTO q) {
		qs.add(q);
	}
	
	@PutMapping("/api/quiz/update")
	public QuizDTO update(@RequestBody QuizDTO q, @RequestParam("id") Long id) {
		return qs.update(q, id);
	}
	
	@DeleteMapping("/api/quiz/delete")
	public void delete(@RequestParam("id") Long id) {
		qs.delete(id);
	}
	
	@GetMapping("/api/quiz/getFullQuiz")
	public QuizProiezione getFull(@RequestParam("id") Long id) {
		return qs.getFullQuiz(id);
	}
	
}
