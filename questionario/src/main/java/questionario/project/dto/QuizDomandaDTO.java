package questionario.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizDomandaDTO {
    private Long id;
    private Long quizId;
    private Long domandaId;
}
