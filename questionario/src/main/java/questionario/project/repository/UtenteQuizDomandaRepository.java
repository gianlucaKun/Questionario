package questionario.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import questionario.project.entita.UtenteQuizDomanda;

public interface UtenteQuizDomandaRepository extends JpaRepository<UtenteQuizDomanda,Long>{
	
	@Query(value="SELECT * FROM questionario.utente_quiz_domanda where utente_quiz_id = :idQuizUtente and domanda_id = :idDomanda ORDER BY id DESC LIMIT 1;", nativeQuery = true)
	UtenteQuizDomanda getLastSubmit (@Param("idDomanda") Long idD, @Param("idQuizUtente") Long idQU);

}
