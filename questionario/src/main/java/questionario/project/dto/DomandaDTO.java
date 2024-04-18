package questionario.project.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import questionario.project.entita.Quiz;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DomandaDTO {
	
    private Long id;
    private String testo;

}
