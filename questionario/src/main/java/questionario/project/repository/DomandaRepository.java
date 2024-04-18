package questionario.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import questionario.project.entita.Domanda;

public interface DomandaRepository extends JpaRepository<Domanda,Long>{

}
