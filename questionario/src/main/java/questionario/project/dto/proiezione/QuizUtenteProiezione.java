package questionario.project.dto.proiezione;

import java.util.List;

import lombok.Data;

@Data
public class QuizUtenteProiezione {
	
	private Long id;
	
	private String username;
	private List<QuizByUtenteProiezione> listaDeiQuiz;

}
