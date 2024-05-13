package questionario.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import questionario.project.entita.Risposta;

public interface RispostaRepository extends JpaRepository<Risposta,Long>{
	
	List<Risposta> findByDomandaId(Long id);
	
	@Query(value = "SELECT id FROM questionario.risposta where domanda_id = :input", nativeQuery = true)
	List<Long> trovaIdRisposte(@Param("input") Long id);
	
}
