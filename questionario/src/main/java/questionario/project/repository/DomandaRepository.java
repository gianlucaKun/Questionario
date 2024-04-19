package questionario.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import questionario.project.entita.Domanda;

@Repository
public interface DomandaRepository extends JpaRepository<Domanda,Long>{

}
