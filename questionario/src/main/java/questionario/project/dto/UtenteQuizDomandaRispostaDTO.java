package questionario.project.dto;

import lombok.Data;

@Data
public class UtenteQuizDomandaRispostaDTO {
    private Long id;
    private Long utenteQuizDomandaId;
    private Long rispostaId;
    private Boolean checked;
}