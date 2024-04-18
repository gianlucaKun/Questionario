package questionario.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import questionario.project.dto.QuizDomandaDTO;
import questionario.project.entita.QuizDomanda;

@Mapper
public interface QuizDomandaMapper {

    QuizDomandaMapper INSTANCE = Mappers.getMapper(QuizDomandaMapper.class);

    @Mapping(source = "quiz.id", target = "quizId")
    @Mapping(source = "domanda.id", target = "domandaId")
    QuizDomandaDTO quizDomandaToDto(QuizDomanda quizDomanda);

    @Mapping(source = "quizId", target = "quiz.id")
    @Mapping(source = "domandaId", target = "domanda.id")
    QuizDomanda quizDomandaDtoToEntity(QuizDomandaDTO quizDomandaDTO);
}
