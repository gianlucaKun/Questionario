package questionario.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import questionario.project.entita.Quiz;

public interface QuizRepository extends JpaRepository<Quiz,Long>{

}
