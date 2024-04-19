package questionario.project.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import questionario.project.dto.UtenteQuizDTO;
import questionario.project.entita.UtenteQuiz;

@Mapper
public interface UtenteQuizMapper {

    UtenteQuizMapper INSTANCE = Mappers.getMapper(UtenteQuizMapper.class);

    @Mapping(source = "utente.id", target = "utenteId")
    @Mapping(source = "quiz.id", target = "quizId")
    UtenteQuizDTO utenteQuizToDto(UtenteQuiz utenteQuiz);

    @Mapping(source = "utenteId", target = "utente.id")
    @Mapping(source = "quizId", target = "quiz.id")
    UtenteQuiz utenteQuizDtoToEntity(UtenteQuizDTO utenteQuizDTO);

	List<UtenteQuizDTO> utenteQuizToDtoList(List<UtenteQuiz> all);
}
