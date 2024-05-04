package questionario.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import questionario.project.entita.UtenteQuizDomanda;

public interface UtenteQuizDomandaRepository extends JpaRepository<UtenteQuizDomanda,Long>{

	UtenteQuizDomanda findByUtenteQuizIdAndDomandaId(Long utenteQuizId, Long domandaId);

//	boolean existsByUtenteQuizId(Long utenteQuizId);


}
