package questionario.project.entita;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtenteQuizDomanda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private UtenteQuiz utenteQuiz;

	@ManyToOne
	private Domanda domanda;

	private String utenteNote;
	private String teacherNotes;
	private Boolean doLater;
	private Long points;
	
    @OneToMany(mappedBy = "UtenteQuizDomanda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UtenteQuizDomandaRisposta> risposte;
}
