package questionario.project.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import questionario.project.dto.RispostaDTO;
import questionario.project.entita.Risposta;

@Mapper
public interface RispostaMapper {
	
	RispostaMapper INSTANCE = Mappers.getMapper(RispostaMapper.class);
	
	@Mapping(source="domanda.id", target="domandaId")
	RispostaDTO toDTO(Risposta r);
	
	@Mapping(source="domandaId", target="domanda.id")
	Risposta toEntity(RispostaDTO rDTO);
	
	List<RispostaDTO> toDTOList(List<Risposta> r);

	List<Risposta> toEntityList(List<RispostaDTO> rDTO);

}
