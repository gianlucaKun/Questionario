package questionario.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import questionario.project.dto.UtenteDTO;
import questionario.project.entita.Utente;
import questionario.project.mapper.UtenteMapper;
import questionario.project.repository.QuizRepository;
import questionario.project.repository.UtenteRepository;

@Service
public class UtenteService {

	@Autowired
	UtenteRepository ur;
	
	@Autowired
	UtenteMapper um;
	
	//crudBasilari
	public void add(UtenteDTO u) {
		ur.save(um.toEntity(u));
	}
	
	public UtenteDTO update(UtenteDTO un, Long id) {
		Utente u = ur.findById(id).orElse(null);
		u.setUsername(un.getUsername() != null ? un.getUsername() : u.getUsername());
		ur.save(u);
		return um.toDTO(u);
	}
	
	@Autowired
	QuizRepository qr;
	
	public void addQuiz(Long id, Long id_quiz) {
		Utente u = ur.findById(id).orElse(null);
		u.getQuiz().add(qr.findById(id_quiz).orElse(null));
		ur.save(u);
	}
	
	public void delete(Long id) {
		ur.deleteById(id);
	}
	
	public List<UtenteDTO> getAll() {
		return um.toDTOList(ur.findAll());
	}
	
	public UtenteDTO finByID(Long id) {
		return um.toDTO(ur.findById(id).orElse(null));
	}

	
}
