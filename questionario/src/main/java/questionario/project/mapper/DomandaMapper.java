package questionario.project.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import questionario.project.dto.DomandaDTO;
import questionario.project.entita.Domanda;

@Mapper
public interface DomandaMapper {
	
	DomandaMapper INSTANCE = Mappers.getMapper(DomandaMapper.class);
	
	DomandaDTO toDTO(Domanda d);
	
	Domanda toEntity(DomandaDTO dDTO);
	
	List<DomandaDTO> toDTOList(List<Domanda> d);
	
	List<Domanda> toEntityList(List<DomandaDTO> dDTO);

}
