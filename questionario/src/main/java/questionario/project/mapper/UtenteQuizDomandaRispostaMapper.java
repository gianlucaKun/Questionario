package questionario.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import questionario.project.dto.UtenteQuizDomandaRispostaDTO;
import questionario.project.entita.UtenteQuizDomandaRisposta;

@Mapper
public interface UtenteQuizDomandaRispostaMapper {

    UtenteQuizDomandaRispostaMapper INSTANCE = Mappers.getMapper(UtenteQuizDomandaRispostaMapper.class);

    @Mapping(source = "utenteQuizDomanda.id", target = "utenteQuizDomandaId")
    @Mapping(source = "risposta.id", target = "rispostaId")
    UtenteQuizDomandaRispostaDTO utenteQuizDomandaRispostaToDto(UtenteQuizDomandaRisposta utenteQuizDomandaRisposta);

    @Mapping(source = "utenteQuizDomandaId", target = "utenteQuizDomanda.id")
    @Mapping(source = "rispostaId", target = "risposta.id")
    UtenteQuizDomandaRisposta utenteQuizDomandaRispostaDtoToEntity(UtenteQuizDomandaRispostaDTO utenteQuizDomandaRispostaDTO);
}
