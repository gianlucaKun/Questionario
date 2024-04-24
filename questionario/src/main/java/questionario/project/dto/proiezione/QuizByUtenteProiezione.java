package questionario.project.dto.proiezione;

import lombok.Data;

@Data
public class QuizByUtenteProiezione {
	
	private Long id;
	private Long quizId;
	private Long utenteId;
	private String username;
	
	/*
	SELECT utente.id as utente_id, utente.username as username, quiz.id as quiz_id, quiz.titolo as titolo_quiz,
	quiz.descrizione as quiz_descrizione, domanda.testo as domanda, risposta.testo as risposta, risposta.corretta as corretta,
	case when r.testo = risposta.testo then null else r.testo END AS risposta_corretta
	FROM questionario.utente_quiz_domanda_risposta
	inner join questionario.utente_quiz_domanda on utente_quiz_domanda_risposta.utente_quiz_domanda_id = utente_quiz_domanda.id
	inner join questionario.utente_quiz on utente_quiz_domanda.utente_quiz_id = utente_quiz.id 
	inner join questionario.quiz on utente_quiz.quiz_id = quiz.id 
	inner join questionario.utente on utente_quiz.utente_id = utente.id
	inner join questionario.risposta on utente_quiz_domanda_risposta.risposta_id = risposta.id
	inner join questionario.domanda on risposta.domanda_id = domanda.id
	left join questionario.risposta r on domanda.id = r.domanda_id
	where r.corretta = 1 and utente_id = 3 and quiz_id = 1
	 */
	
//	private List<DomandeUtenteProiezione> lista;

}
