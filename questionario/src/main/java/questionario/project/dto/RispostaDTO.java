package questionario.project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RispostaDTO {
	
    private Long id;
    private String testo;
    private Boolean corretta;
    private Long domandaId;
}
