package questionario.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import questionario.project.dto.QuizDTO;
import questionario.project.entita.Quiz;
import questionario.project.mapper.QuizMapper;
import questionario.project.repository.QuizRepository;
import questionario.project.repository.UtenteRepository;

@Service
public class QuizService {
	
	@Autowired
	QuizRepository qr;
	
	@Autowired
	QuizMapper qm;
	
	//crud essenziali
	public void addNew(QuizDTO q) {
		qr.save(qm.toEntity(q));
	}
	
	public QuizDTO update(QuizDTO qn, Long id) {
		Quiz q = qr.findById(id).orElse(null);
		q.setDescrizione(qn.getDescrizione() != null ? qn.getDescrizione() : q.getDescrizione());
		q.setTitolo(qn.getTitolo() != null ? qn.getTitolo() : q.getTitolo());
		qr.save(q);
		return qm.toDTO(q);
	}
	
	public void delete(Long id) {
		qr.deleteById(id);
	}
	
	public QuizDTO getById(Long id) {
		return qm.toDTO(qr.findById(id).orElse(null));
	}
	@Autowired
	UtenteRepository ur;
	
	public void addUtente(Long id, Long id_utente) {
		Quiz q = qr.findById(id).orElse(null);
		q.getUtente().add(ur.findById(id_utente).orElse(null));
		qr.save(q);
	}
	
	public List<QuizDTO> getAll() {
		return qm.toDTOList(qr.findAll());
	}

}
