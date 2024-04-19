package questionario.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import questionario.project.dto.QuizDTO;
import questionario.project.dto.proiezione.QuizProiezione;
import questionario.project.entita.Quiz;
import questionario.project.mapper.QuizMapper;
import questionario.project.repository.QuizDomandaRepository;
import questionario.project.repository.QuizRepository;

@Service
public class QuizService {

	@Autowired
	QuizRepository qr;
	
	@Autowired
	QuizMapper qm;
	
	//crud basic
	//add
	public void add(QuizDTO q) {
		qr.save(qm.toEntity(q));
	}
	//select * all
	public List<QuizDTO> selectAll(){
		return qm.toDTOList(qr.findAll());
	}
	//select where id
	public QuizDTO selectById(Long id) {
		return qm.toDTO(qr.findById(id).orElse(null));
	}
	//update
	public QuizDTO update(QuizDTO qb, Long id) {
		Quiz q = qr.findById(id).orElse(null);
		q.setDescrizione(qb.getDescrizione() != null ? qb.getDescrizione() : q.getDescrizione());
		q.setTitolo(qb.getTitolo() != null ? qb.getTitolo() : q.getTitolo());
		qr.save(q);
		return qm.toDTO(q);
	}
	//delete
	public void delete(Long id) {
		qr.deleteById(id);
	}
	
	@Autowired
	DomandaService ds;
	
	@Autowired
	QuizDomandaRepository qdr;
	
	//getAllquiz
	public QuizProiezione getAllQuiz(Long id) {
		QuizProiezione q = new QuizProiezione();
		q.setId(id);
		q.setTitolo(qr.findById(id).orElse(null).getTitolo());
		q.setDescrizione(qr.findById(id).orElse(null).getDescrizione());
		
		for(Long q2 : qdr.findDomandebyQuiz(id)) {
			
		}
		
		return null;
	}
}
