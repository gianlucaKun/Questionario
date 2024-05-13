package questionario.project.dto;

import java.util.List;

import lombok.Data;

@Data
public class UtenteQuizDomandaDTO {
    private Long id;
    private Long utenteQuizId;
    private Long domandaId;
    private String utenteNote;
    private String teacherNotes;
    private Boolean doLater;
    private Long points;
    private List<UtenteQuizDomandaRispostaDTO> risposte; // Aggiunto per includere le domande associate
}
