package questionario.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import questionario.project.entita.UtenteQuiz;

public interface UtenteQuizRepository extends JpaRepository<UtenteQuiz,Long>{

    @Query(value = "SELECT * FROM questionario.utenteQuiz WHERE utenteId = ?1 AND quizId = ?2", nativeQuery = true)
    UtenteQuiz findByUtenteIdAndQuizId(Long utenteId, Long quizId);

}
