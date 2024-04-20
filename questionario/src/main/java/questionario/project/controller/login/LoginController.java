package questionario.project.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import questionario.project.dto.UtenteDTO;
import questionario.project.service.UtenteService;
import questionario.project.service.security.JwtService;

@RestController
@RequestMapping("/Jwt")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UtenteService us;
	
    @PostMapping("/loginUtente")
    public ResponseEntity<String> login(@RequestBody UtenteDTO loginRequest) {
        // Esegui l'autenticazione dell'utente
        boolean authenticationResult = us.login(loginRequest.getUsername(), loginRequest.getPassword());
        
        if (authenticationResult) {
        	
            // Se l'autenticazione ha successo, genera un token JWT per l'utente
            String token = jwtService.generateToken(loginRequest.getUsername());

            // Restituisci il token nella risposta
            return ResponseEntity.ok(token);
        } else {
            // Se l'autenticazione fallisce, restituisci una risposta con codice di stato 401 (Non autorizzato)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Autenticazione fallita");
        }
    }

}
