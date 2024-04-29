package questionario.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import questionario.project.dto.UtenteDTO;
import questionario.project.entita.Utente;
import questionario.project.exception.UserException;
import questionario.project.mapper.UtenteMapper;
import questionario.project.repository.UtenteRepository;
import questionario.project.service.security.JwtService;

@Service
public class UtenteService {
	
	@Autowired
	UtenteRepository ur;
	
	@Autowired
	UtenteMapper um;
	
	@Autowired
	JwtService jwtService;
	
	//crud basic
	//add
	public void add(UtenteDTO u) {
		ur.save(um.toEntity(u));
	}
	//select * all
	public List<UtenteDTO> selectAll() {
		return um.toDTOList(ur.findAll());
	}
	//select where id
	public UtenteDTO selectById(Long id) {
		return um.toDTO(ur.findById(id).orElse(null));
	}
	//update
	public UtenteDTO update(UtenteDTO ub,Long id) {
		Utente u = ur.findById(id).orElse(null);
		u.setUsername(ub.getUsername() != null ? ub.getUsername() : u.getUsername());
		ur.save(u);
		return um.toDTO(u);
	}
	//delete
	public void delete(Long id) {
		ur.deleteById(id);
	}
	
	public boolean login(String username, String password) {
        // Recupera l'utente dal repository in base al nome utente
        Optional<Utente> user = ur.findByUsername(username);

        // Controlla se l'utente esiste e se la password è corretta
        if (user != null && user.get().getPassword().equals(password)) {
        	
            return true; // L'autenticazione ha avuto successo
        } else {
            return false; // L'autenticazione ha fallito
        }
	}
	public Utente findUserProfileByJwt(String jwt) throws UserException {
		
		String username = jwtService.getUsernameFromJwtToken(jwt);
		
		System.out.println("USERNAME " + username);
		
		Optional<Utente> user = ur.findByUsername(username);
		
		if(user.isEmpty()) {
			throw new UserException("non esiste un utente con questo username: " + username);
		}
		
		System.out.println("username utente " + user.get().getUsername());
		return user.get();
	}
	
}
