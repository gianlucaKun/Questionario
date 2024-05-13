package questionario.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import questionario.project.dto.UtenteDTO;
import questionario.project.entita.Utente;
import questionario.project.mapper.UtenteMapper;
import questionario.project.repository.UtenteRepository;

@Service
public class UtenteService {
	
	@Autowired
	UtenteRepository ur;
	
	@Autowired
	UtenteMapper um;
	
	//crud basic
	//add
	public UtenteDTO add(UtenteDTO u) {
		Utente utente = ur.save(um.toEntity(u));
		return um.toDTO(utente);
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
		u.setCognome(ub.getCognome() != null ? ub.getCognome() : u.getCognome());
		u.setNome(ub.getNome() != null ? ub.getNome() : u.getNome());
		u.setPassword(ub.getPassword() != null ? ub.getPassword() : u.getPassword());
		u.setImgUrl(ub.getImgUrl() != null ? ub.getImgUrl() : u.getImgUrl());
		u.setImgCopertina(ub.getImgCopertina() != null ? ub.getImgCopertina() : u.getImgCopertina());
		ur.save(u);
		return um.toDTO(u);
	}
	//delete
	public void delete(Long id) {
		ur.deleteById(id);
	}
	//trova i dati dell'utente tramite username
	public UtenteDTO trovaUsername(String username) {
		UtenteDTO u = um.toDTO(ur.findByUsername(username));
		System.out.println(u.getPassword());
		return u;
	}
	
	public boolean login(String username, String password) {
        // Recupera l'utente dal repository in base al nome utente
        Utente user = ur.findByUsername(username);

        // Controlla se l'utente esiste e se la password è corretta
        if (user != null && user.getPassword().equals(password)) {
        	
            return true; // L'autenticazione ha avuto successo
        } else {
            return false; // L'autenticazione ha fallito
        }
	}
	
}
