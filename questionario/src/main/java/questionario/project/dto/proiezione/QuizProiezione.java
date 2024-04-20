package questionario.project.dto.proiezione;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuizProiezione {
    
    private Long id;
    private String titolo, descrizione;
    
    private List<DomandaProiezione> listaDomande;
    
    // Costruttore che inizializza la lista delle domande
    public QuizProiezione() {
        this.listaDomande = new ArrayList<>();
    }

}

