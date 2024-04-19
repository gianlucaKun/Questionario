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

import questionario.project.dto.DomandaDTO;
import questionario.project.dto.proiezione.DomandaProiezione;
import questionario.project.service.DomandaService;

@RestController
@RequestMapping("/domanda")
@CrossOrigin(origins = "http://localhost:4200")
public class DomandaController {
	
	@Autowired
	DomandaService ds;
	
	@GetMapping("/all")
	public List<DomandaDTO> getAll() {
		return ds.selectAll();
	}
	
	@GetMapping("/find")
	public DomandaDTO select(@RequestParam("id") Long id) {
		return ds.selectById(id);
	}
	
	@PostMapping("/add")
	public void add(@RequestBody DomandaDTO d) {
		ds.add(d);
	}
	
	@PutMapping("/update")
	public DomandaDTO update(@RequestBody DomandaDTO d, @RequestParam("id") Long id) {
		return ds.update(d, id);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestParam("id") Long id) {
		ds.delete(id);
	}
	
	@GetMapping("/risposte")
	public DomandaProiezione getRisposte(@RequestParam("id") Long id) {
		return ds.getRisposte(id);
	}
	
	
}
