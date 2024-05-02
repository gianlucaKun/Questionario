package questionario.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import questionario.project.dto.UtenteDTO;
import questionario.project.entita.Utente;
import questionario.project.exception.UserException;
import questionario.project.service.UtenteService;

@RestController
@RequestMapping("/api/utente")
@CrossOrigin(origins = "http://localhost:4200")
public class UtenteController {

	@Autowired
	UtenteService us;

	@GetMapping("/all")
	public List<UtenteDTO> getAll() {
		return us.selectAll();
	}

	@GetMapping("/find")
	public UtenteDTO select(@RequestParam("id") Long id) {
		return us.selectById(id);
	}


	
	@PostMapping("/add")
	public void add(@RequestBody UtenteDTO u) {
		us.add(u);
	}

	@PutMapping("/update")
	public UtenteDTO update(@RequestBody UtenteDTO u, @RequestParam("id") Long id) {
		return us.update(u, id);
	}

	@DeleteMapping("/delete")
	public void delete(@RequestParam("id") Long id) {
		us.delete(id);
	}

	@GetMapping("/profile")
	public ResponseEntity<Utente> getUserProfileHandler(@RequestHeader("Authorization") String jwt) throws UserException {

	    System.out.println("stampo il valore del token " + jwt);
	    System.out.println("sono nel controller utente controller ");

	    
	    if (!jwt.startsWith("Bearer ")) {
	        jwt = "Bearer " + jwt;
	    }

	    Utente user = us.findUserProfileByJwt(jwt);

	    return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
	}

	
	@GetMapping("/userProfile")
	public UtenteDTO userProfile(@RequestParam("username") String username) throws UserException {
		return us.findByUsername(username);
	}
}
