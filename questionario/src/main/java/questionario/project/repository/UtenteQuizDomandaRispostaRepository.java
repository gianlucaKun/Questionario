package questionario.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import questionario.project.entita.UtenteQuizDomandaRisposta;

public interface UtenteQuizDomandaRispostaRepository extends JpaRepository<UtenteQuizDomandaRisposta,Long>{
	
	@Query(value="SELECT * FROM questionario.utente_quiz_domanda_risposta where risposta_id = :risposta and utente_quiz_domanda_id = :idUQD", nativeQuery = true)
	Long returnId(@Param("risposta") Long idRisposta, @Param("idUQD") Long idUQD);

}
