package questionario.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import questionario.project.dto.UtenteQuizDomandaDTO;
import questionario.project.entita.UtenteQuizDomanda;

@Mapper
public interface UtenteQuizDomandaMapper {

    UtenteQuizDomandaMapper INSTANCE = Mappers.getMapper(UtenteQuizDomandaMapper.class);

    @Mapping(source = "utenteQuiz.id", target = "utenteQuizId")
    @Mapping(source = "domanda.id", target = "domandaId")
    UtenteQuizDomandaDTO utenteQuizDomandaToDto(UtenteQuizDomanda utenteQuizDomanda);

    @Mapping(source = "utenteQuizId", target = "utenteQuiz.id")
    @Mapping(source = "domandaId", target = "domanda.id")
    UtenteQuizDomanda utenteQuizDomandaDtoToEntity(UtenteQuizDomandaDTO utenteQuizDomandaDTO);
}
