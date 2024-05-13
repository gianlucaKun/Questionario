package questionario.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import questionario.project.entita.UtenteQuiz;

public interface UtenteQuizRepository extends JpaRepository<UtenteQuiz,Long>{
	
	@Query(value="SELECT * FROM questionario.utente_quiz where utente_id = :input ORDER BY start_time DESC LIMIT 1;", nativeQuery = true)
	Long getIdAppenaCreato(@Param("input") Long id);
	
}
