package questionario.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtenteDTO {
	
    private Long id;
    
    private String nome;
    private String cognome;
    
    private String username;
    private String password;
    
    private String imgUrl;
    private String imgCopertina;

}
