package questionario.project.dto.proiezione;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import questionario.project.dto.RispostaDTO;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DomandaProiezione {
    private Long id;
    private String domandaTesto;
    private Boolean doLater = false;
    private List<RispostaDTO> listaRisposte;
}
