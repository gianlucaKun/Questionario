package questionario.project.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import questionario.project.dto.UtenteDTO;
import questionario.project.entita.Utente;

@Mapper
public interface UtenteMapper {
	
	UtenteMapper INSTANCE = Mappers.getMapper(UtenteMapper.class);
	
	UtenteDTO toDTO(Utente u);
	
	Utente toEntity(UtenteDTO uDTO);
	
	List<UtenteDTO> toDTOList(List<Utente> u);
	
	List<Utente> toEntityList(List<UtenteDTO> uDTO);
	
}
