package questionario.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import questionario.project.entita.Utente;
import questionario.project.exception.UserException;
import questionario.project.repository.UtenteRepository;
import questionario.project.request.LoginRequest;
import questionario.project.response.AuthResponse;
import questionario.project.service.UtenteService;
import questionario.project.service.security.JwtService;
import questionario.project.service.security.util.CustomeUserServiceImplementation;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	PasswordEncoder pwE;

	@Autowired
	private UtenteService us;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private CustomeUserServiceImplementation CustomeUS;
	
	@Autowired
	private UtenteRepository ur;

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody Utente utente) throws UserException {
		
		
	System.out.println("SONO QUI DENTRO SIGN UP");
	String username = utente.getUsername();
	String password = utente.getPassword();
	
	System.out.println("DENTRO SIGN UP :  username : " + username + " ---------- " + "password: " + password);
	
	Optional<Utente> isUsernameExist = ur.findByUsername(username);
	
	if (isUsernameExist.isPresent()) {
		throw new UserException("Username gia presente in un altro account");
	}
	
	Utente nuovoUtente = new Utente () ;
	nuovoUtente.setUsername(username);
	nuovoUtente.setPassword(pwE.encode(password));
	
	Utente savedUtente = ur.save(nuovoUtente);
	
	Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
	SecurityContextHolder.getContext().setAuthentication(authentication);
	
	String token = jwtService.generateToken(authentication);
	
	AuthResponse authResponse = new AuthResponse();
	authResponse.setJwt(token);
	authResponse.setMessage("Registrazione Avvenuta");
	

	return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
	
	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) throws UserException {

		System.out.println("SIGN IN :    ---------  SONO QUI");

		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();

		System.out.println("username: " + username + " ------------- " + "password: " + password);

		Authentication authentication = authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtService.generateToken(authentication);
		AuthResponse authResponse = new AuthResponse();
		authResponse.setMessage("LOGIN EFFETTUATO");
		authResponse.setJwt(token);

		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);

	}

	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = CustomeUS.loadUserByUsername(username);

		System.out.println("sign in userDetails - " + userDetails);

		if (userDetails == null) {
			System.out.println("sign in userDetails - null " + userDetails);
			throw new BadCredentialsException("Invalid username or password");
		}
		if (!pwE.matches(password, userDetails.getPassword())) {
			System.out.println("sign in userDetails - password not match " + userDetails);
			throw new BadCredentialsException("Invalid username or password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
}
