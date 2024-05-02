package questionario.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import questionario.project.dto.UtenteDTO;
import questionario.project.entita.Utente;
import questionario.project.exception.UserException;
import questionario.project.mapper.UtenteMapper;
import questionario.project.repository.UtenteRepository;

@Service
public class UtenteService {
	
	@Autowired
	UtenteRepository ur;
	
	@Autowired
	UtenteMapper um;
	
	 @Autowired
	    private PasswordEncoder passwordEncoder;
	 
	//crud basic
	public UtenteDTO add(UtenteDTO utenteDTO) {

        Utente utente = new Utente();
        utente.setUsername(utenteDTO.getUsername());

        utente.setPassword(passwordEncoder.encode(utenteDTO.getPassword()));
        

        ur.save(utente);
        
        return um.toDTO(utente);
	}
	
    public Utente login(String username, String password) {
 
        Utente utente = ur.findByUsername(username);
        

        if (utente != null && passwordEncoder.matches(password, utente.getPassword())) {
            return utente;
        } else {
            return null;
        }
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
	
//	public boolean login(String username, String password) {
//        // Recupera l'utente dal repository in base al nome utente
//        Utente user = ur.findByUsername(username);
//
//        // Controlla se l'utente esiste e se la password è corretta
//        if (user != null && user.getPassword().equals(password)) {
//        	
//            return true; // L'autenticazione ha avuto successo
//        } else {
//            return false; // L'autenticazione ha fallito
//        }
//	}
//	public Utente findUserProfileByJwt(String jwt) throws UserException {
//		
//		
//		
//		String username = jwtService.getUsernameFromJwtToken(jwt);
//		
//		System.out.println("USERNAME " + username);
//		
//		Optional<Utente> user = ur.findByUsername(username);
//		
//		if(user.isEmpty()) {
//			throw new UserException("non esiste un utente con questo username: " + username);
//		}
//		
//		System.out.println("username utente " + user.get().getUsername());
//		return user.get();
//	}
	public UtenteDTO findByUsername(String username) throws UserException {

		Utente utente = ur.findByUsername(username);
		
		if (utente != null) {
			UtenteDTO savedUtente = new UtenteDTO();
			savedUtente.setId(utente.getId());
			savedUtente.setPassword(utente.getPassword());
			savedUtente.setUsername(username);
			return savedUtente;
		} else {
			throw new UserException("non esiste un utente con questo username: " + username);
		}
	}
	
}
