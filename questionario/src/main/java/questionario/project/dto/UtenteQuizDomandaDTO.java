package questionario.project.dto;

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
}
