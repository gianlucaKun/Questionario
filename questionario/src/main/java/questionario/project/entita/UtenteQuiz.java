package questionario.project.entita;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtenteQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    @ManyToOne
    private Utente  utente;
    
    @ManyToOne
    private Quiz  quiz;
    
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String note;

}