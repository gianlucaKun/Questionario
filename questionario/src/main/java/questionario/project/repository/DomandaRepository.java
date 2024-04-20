package questionario.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import questionario.project.entita.Domanda;

@Repository
public interface DomandaRepository extends JpaRepository<Domanda,Long>{

	@Query("SELECT id FROM Domanda WHERE testo = :domandaTesto")
	Long findDomandaIdByTesto(@Param("domandaTesto") String domandaTesto);

}
