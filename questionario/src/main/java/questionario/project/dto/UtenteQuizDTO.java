package questionario.project.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UtenteQuizDTO {
    private String id;
    private Long utenteId;
    private Long quizId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String note;
    private List<UtenteQuizDomandaDTO> domande; // Aggiunto per includere le domande associate
}
