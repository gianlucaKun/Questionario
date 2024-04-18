package questionario.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import questionario.project.entita.Risposta;

public interface RispostaRepository extends JpaRepository<Risposta,Long>{
	
}
