package questionario.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import questionario.project.dto.UtenteQuizDTO;
import questionario.project.entita.UtenteQuiz;
import questionario.project.mapper.UtenteQuizMapper;
import questionario.project.repository.QuizRepository;
import questionario.project.repository.UtenteQuizRepository;
import questionario.project.repository.UtenteRepository;

@Service
public class UtenteQuizService {
	
	@Autowired
	UtenteQuizRepository uqr;
	
	@Autowired
	UtenteQuizMapper uqm;
	//crud basic
	//aggiungi
	public UtenteQuizDTO add(UtenteQuizDTO q) {
		UtenteQuiz r = new UtenteQuiz();
		r = uqm.utenteQuizDtoToEntity(q);
		
		uqr.save(r);
		
		return uqm.utenteQuizToDto(r);
	}
	//seletc* all
	public List<UtenteQuizDTO> selectAll(){
		return uqm.utenteQuizToDtoList(uqr.findAll());
	}
	//selecte where id
	public UtenteQuizDTO selectByID(Long id) {
		return uqm.utenteQuizToDto(uqr.findById(id).orElse(null));
	}
	@Autowired
	UtenteRepository ur;
	@Autowired
	QuizRepository qr;
	//update
	public UtenteQuizDTO update(UtenteQuizDTO uq, Long id) {
		UtenteQuiz u = uqr.findById(id).orElse(null);
		u.setUtente(uq.getUtenteId() != null ? ur.findById(uq.getUtenteId()).orElse(null) : u.getUtente());
		u.setQuiz(uq.getQuizId() != null ? qr.findById(uq.getQuizId()).orElse(null) : u.getQuiz());
		u.setNote(uq.getNote() != null ? uq.getNote() : u.getNote());
		u.setStartTime(uq.getStartTime() != null ? uq.getStartTime() : u.getStartTime());
		u.setEndTime(uq.getEndTime() != null ? uq.getEndTime() : u.getEndTime());
		uqr.save(u);
		return uqm.utenteQuizToDto(u);
	}
	//delete
	public void delete(Long id) {
		uqr.deleteById(id);
	}
	public UtenteQuiz findIfExist(Long utenteId, Long quizId) {
		UtenteQuiz exist = uqr.findByUtenteIdAndQuizId(utenteId, quizId);
		return exist;
	}
	
	public Long getIdCreated(Long id) {
		return uqr.getIdAppenaCreato(id);
	}
	
}
