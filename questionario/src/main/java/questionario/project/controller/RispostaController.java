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

import questionario.project.dto.RispostaDTO;
import questionario.project.service.RispostaService;

@RestController
@RequestMapping("/risposta")
@CrossOrigin(origins = "http://localhost:5173/")
public class RispostaController {
	
	@Autowired
	RispostaService rs;
	
	@GetMapping("/all")
	public List<RispostaDTO> getAll() {
		return rs.selectAll();
	}
	
	@GetMapping("/find")
	public RispostaDTO seletc(@RequestParam Long id) {
		return rs.selectById(id);
	}
	
	@GetMapping("/trova_risposte")
	public List<Long> findById(@RequestParam Long id){
		return rs.selectByIdDomanda(id);
	}
	
	@PostMapping("/admin/add")
	public void add(@RequestBody RispostaDTO r) {
		rs.add(r);
	}
	
	@PutMapping("/admin/update")
	public RispostaDTO update(@RequestBody RispostaDTO r, @RequestParam("id") Long id) {
		return rs.update(id, r);
	}
	
	@DeleteMapping("/admin/delete")
	public void delete(@RequestParam Long id) {
		rs.delete(id);
	}

}
