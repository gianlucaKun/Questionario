package questionario.project.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import questionario.project.dto.RispostaDTO;
import questionario.project.service.RispostaService;

@RestController
@RequestMapping("/risposta")
public class RispostaRestController {
	
	@Autowired
	RispostaService rs;
	
	@GetMapping("/all")
	public List<RispostaDTO> getAll() {
		return rs.getAll();
	}
	
	@GetMapping("/findById")
	public RispostaDTO findbyID(@RequestParam("id") Long id) {
		return rs.getByID(id);
	}
	
	@PostMapping("/addRisposta")
	public void addRisposta(@RequestBody RispostaDTO r) {
		rs.addNew(r);
	}
	
	
}
