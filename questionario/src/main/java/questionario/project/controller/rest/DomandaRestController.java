package questionario.project.controller.rest;

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

import questionario.project.dto.DomandaDTO;
import questionario.project.dto.RispostaDTO;
import questionario.project.service.DomandaService;

@RestController
@RequestMapping("/domanda")
public class DomandaRestController {
	
	@Autowired
	DomandaService ds;
	
	@GetMapping("/all")
	public List<DomandaDTO> getAll(){
		return ds.getAll();
	}
	
	@GetMapping("/findById")
	public DomandaDTO findById(@RequestParam("id") Long id) {
		return ds.findById(id);
	}
	
	@PostMapping("/add")
	public void addDomanda(@RequestBody DomandaDTO d) {
		ds.addNew(d);
	}
	
	@PutMapping("/update")
	public DomandaDTO update(@RequestBody DomandaDTO d, @RequestParam("id") Long id) {
		return ds.update(d, id);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestParam("id") Long id) {
		ds.delete(id);
	}
	
	
}
