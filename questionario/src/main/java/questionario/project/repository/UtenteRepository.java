package questionario.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import questionario.project.entita.Utente;

public interface UtenteRepository extends JpaRepository<Utente,Long>{
	
	public Optional<Utente> findByUsername(String username);
	
}
