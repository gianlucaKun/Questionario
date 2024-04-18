package questionario.project.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UtenteQuizDTO {
    private String id;
    private String utenteId;
    private Long quizId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String note;
}
