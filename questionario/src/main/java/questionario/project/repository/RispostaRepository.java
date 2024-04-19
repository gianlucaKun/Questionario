package questionario.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import questionario.project.entita.Risposta;

public interface RispostaRepository extends JpaRepository<Risposta,Long>{
	
	List<Risposta> findByDomandaId(Long id);
	
}
