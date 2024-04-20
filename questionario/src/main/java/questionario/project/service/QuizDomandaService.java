package questionario.project.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import questionario.project.dto.QuizDomandaDTO;
import questionario.project.dto.RispostaDTO;
import questionario.project.dto.proiezione.DomandaProiezione;
import questionario.project.dto.proiezione.QuizProiezione;
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
	
	@Autowired
	DomandaRepository dr;

	@Autowired
	QuizRepository qr;
	
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
	
	// Test recuperare il quiz con le varie domande e risposte
	
	public QuizProiezione getQuizWithQuestionsAndAnswers(Long quizId) {
	    List<Object[]> quizData = qdr.findAllDomandaAndRispostaByQuiz(quizId);
	    QuizProiezione quizProiezione = new QuizProiezione();
	    quizProiezione.setId(quizId); // Imposta l'ID del quiz
	    quizProiezione.setListaDomande(new ArrayList<>()); // Inizializza la lista delle domande
	    // Mappa le domande e le relative risposte nei DTO di proiezione
	    quizData.forEach(result -> mapToDomandaProiezione(result, quizProiezione));

	    return quizProiezione;
	}

	private void mapToDomandaProiezione(Object[] result, QuizProiezione quizProiezione) {
	    
	    String domandaTesto = (String) result[4];
	    Long domandaId = dr.findDomandaIdByTesto(domandaTesto);

	    // Verifica se la domanda è già stata mappata precedentemente
	    DomandaProiezione existingDomanda = quizProiezione.getListaDomande().stream()
	            .filter(domanda -> domanda.getId().equals(domandaId))
	            .findFirst()
	            .orElse(null);

	    // Se la domanda non è stata mappata precedentemente, la mappa insieme alle sue risposte
	    if (existingDomanda == null) {
	        existingDomanda = new DomandaProiezione();
	        existingDomanda.setId(domandaId);
	        existingDomanda.setDomandaTesto(domandaTesto);

	        List<RispostaDTO> listaRisposte = qdr.findRisposteByDomanda(domandaId);
	        existingDomanda.setListaRisposte(listaRisposte);

	        // Aggiunge la domanda alla lista di domande nel quizProiezione solo se non è già presente
	        quizProiezione.getListaDomande().add(existingDomanda);
	    } else {
	        // Se la domanda è già stata mappata, aggiungi solo le risposte mancanti alla lista delle risposte
	        List<RispostaDTO> existingRisposte = existingDomanda.getListaRisposte();
	        List<RispostaDTO> risposteToAdd = qdr.findRisposteByDomanda(domandaId);
	        
	        // Aggiungi solo le risposte che non sono già presenti nella lista delle risposte
	        for (RispostaDTO risposta : risposteToAdd) {
	            if (!existingRisposte.contains(risposta)) {
	                existingRisposte.add(risposta);
	            }
	        }
	    }
	}

	
    
	
}
