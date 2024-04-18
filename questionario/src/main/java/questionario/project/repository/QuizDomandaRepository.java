package questionario.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import questionario.project.entita.QuizDomanda;

public interface QuizDomandaRepository extends JpaRepository<QuizDomanda,Long>{

}
