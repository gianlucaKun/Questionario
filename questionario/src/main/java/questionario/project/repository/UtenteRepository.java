package questionario.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import questionario.project.entita.Utente;

public interface UtenteRepository extends JpaRepository<Utente,Long>{
	
	Utente findByUsername(String username);
	
}
