package questionario.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import questionario.project.dto.QuizDomandaDTO;
import questionario.project.entita.QuizDomanda;
import questionario.project.mapper.QuizDomandaMapper;
import questionario.project.repository.DomandaRepository;
import questionario.project.repository.QuizDomandaRepository;
import questionario.project.repository.QuizRepository;

@Service
public class QuizDomandaService {
	
	@Autowired
	QuizDomandaRepository qdr;
	
	@Autowired
	QuizDomandaMapper qdm;
	
	//crud basic
	//add
	public void add(QuizDomandaDTO qd) {
		qdr.save(qdm.quizDomandaDtoToEntity(qd));
	}
	//select * all
	public List<QuizDomandaDTO> selectAll(){
		return qdm.quizDomandaToDtoList(qdr.findAll());
	}
	//select where id
	public QuizDomandaDTO selectById(Long id) {
		return qdm.quizDomandaToDto(qdr.findById(id).orElse(null));
	}
	
	@Autowired
	DomandaRepository dr;
	@Autowired
	QuizRepository qr;
	
	//update
	public QuizDomandaDTO update(QuizDomandaDTO qdb, Long id) {
		QuizDomanda qd = qdr.findById(id).orElse(null);
		qd.setDomanda(qdb.getDomandaId() != null ? dr.findById(qdb.getDomandaId()).orElse(null) : qd.getDomanda());
		qd.setQuiz(qdb.getQuizId() != null ? qr.findById(qdb.getQuizId()).orElse(null) : qd.getQuiz());
		qdr.save(qd);
		return qdm.quizDomandaToDto(qd);
	}
	//delete
	public void delete(Long id) {
		qdr.deleteById(id);
	}
}
