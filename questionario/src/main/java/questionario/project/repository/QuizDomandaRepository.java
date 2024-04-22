package questionario.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import questionario.project.dto.RispostaDTO;
import questionario.project.entita.QuizDomanda;

public interface QuizDomandaRepository extends JpaRepository<QuizDomanda,Long>{
	
    @Query(value = "SELECT domanda_id FROM questionario.quiz_domanda where quiz_id = :input", nativeQuery = true)
    List<Long> findDomandebyQuiz(@Param("input") Long input);

    //TEST
    @Query(value = "SELECT *\r\n"
    		+ "FROM questionario.quiz_domanda\r\n"
    		+ "INNER JOIN questionario.domanda\r\n"
    		+ "    ON domanda.id = quiz_domanda.domanda_id\r\n"
    		+ "INNER JOIN questionario.risposta\r\n"
    		+ "    ON risposta.domanda_id = domanda.id\r\n"
    		+ "WHERE quiz_domanda.quiz_id = :quizId", nativeQuery = true)
    List<Object[]> findAllDomandaAndRispostaByQuiz(@Param("quizId") Long quizId);

    @Query("SELECT new questionario.project.dto.RispostaDTO(r.id, r.testo, r.corretta, r.domanda.id) " +
            "FROM Risposta r " +
            "WHERE r.domanda.id = :domandaId")
    List<RispostaDTO> findRisposteByDomanda(@Param("domandaId") Long domandaId);

//    "SELECT *\r\n"
//	+ "FROM questionario.utente_quiz_domanda\r\n"
//	+ "inner JOIN questionario.utente_quiz\r\n"
//	+ "on utente_quiz.id = utente_quiz_domanda.utente_quiz_id\r\n"
//	+ "inner join questionario.quiz\r\n"
//	+ "on utente_quiz.id = quiz.id\r\n"
//	+ "Inner join questionario.quiz_domanda\r\n"
//	+ "on quiz_domanda.quiz_id = quiz.id\r\n"
//	+ "INNER JOIN questionario.domanda\r\n"
//	+ "    ON domanda.id = quiz_domanda.domanda_id\r\n"
//	+ "INNER JOIN questionario.risposta\r\n"
//	+ "    ON risposta.domanda_id = domanda.id\r\n"
}
