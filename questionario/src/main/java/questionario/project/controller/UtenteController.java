package questionario.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import questionario.project.dto.UtenteDTO;
import questionario.project.service.UtenteService;
import questionario.project.service.security.JwtService;

@RestController
@RequestMapping("/utente")
@CrossOrigin(origins = "http://localhost:4200/")
public class UtenteController {
	
	@Autowired
	UtenteService us;
	
    @Autowired
    private JwtService jwtService;
	
	@GetMapping("/all")
	public List<UtenteDTO> getAll() {
		return us.selectAll();
	}
	
	@GetMapping("/api/find")
	public UtenteDTO select(@RequestParam("id") Long id) {
		return us.selectById(id);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody UtenteDTO u) {
	    UtenteDTO newUser = us.add(u);
	    String token = jwtService.generateToken(newUser.getUsername()); // Metodo per generare il token JWT per il nuovo utente


	    return ResponseEntity.ok(Map.of("token", token));
	}

	
	@PutMapping("/update")
	public UtenteDTO update(@RequestBody UtenteDTO u, @RequestParam("id") Long  id) {
		return us.update(u, id);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestParam("id") Long id) {
		us.delete(id);
	}
	
}
