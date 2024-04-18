package questionario.project.entita;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtenteQuizDomandaRisposta {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @ManyToOne
    private UtenteQuizDomanda UtenteQuizDomanda;
    
    @ManyToOne
    private Risposta risposta;
    
    private Boolean checked;

}
