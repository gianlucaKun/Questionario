package questionario.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import questionario.project.entita.QuizDomanda;

public interface QuizDomandaRepository extends JpaRepository<QuizDomanda,Long>{
	
    @Query(value = "SELECT domanda_id FROM questionario.quiz_domanda where quiz_id = :input", nativeQuery = true)
    List<Long> findDomandebyQuiz(@Param("input") Long input);

}
