package questionario.project.entita;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "utente_quiz")
public class UtenteQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Utente  utente;
    
    @ManyToOne
    private Quiz  quiz;
    
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String note;
    
    @OneToMany(mappedBy = "UtenteQuiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UtenteQuizDomanda> domande;
}
