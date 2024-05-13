package questionario.project.dto.proiezione;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data

public class QuizProiezione {
	
	private Long id;
	private String titolo, descrizione, urlImg;
	
	private List<DomandaProiezione> listaDomande;

}
