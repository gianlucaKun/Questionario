package questionario.project.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
	
    private Long id;
    private String titolo;
    private String descrizione;
    private List<UtenteDTO> utente;
	
}
