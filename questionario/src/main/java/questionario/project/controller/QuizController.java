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
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "http://localhost:5173/")
public class QuizController {
	
	@Autowired
	QuizService qs;
	
	@GetMapping("/all")
	public List<QuizDTO> getAll(){
		return qs.selectAll();
	}
	
	@GetMapping("/find")
	public QuizDTO getMethodName(@RequestParam("id") Long id) {
		return qs.selectById(id);
	}
	
	@PostMapping("/admin/add")
	public void add(@RequestBody QuizDTO q) {
		qs.add(q);
	}
	
	@PutMapping("/admin/update")
	public QuizDTO update(@RequestBody QuizDTO q, @RequestParam("id") Long id) {
		return qs.update(q, id);
	}
	
	@DeleteMapping("/admin/delete")
	public void delete(@RequestParam("id") Long id) {
		qs.delete(id);
	}
	
	@GetMapping("/getFullQuiz")
	public QuizProiezione getFull(@RequestParam("id") Long id) {
		return qs.getFullQuiz(id);
	}
	
}
