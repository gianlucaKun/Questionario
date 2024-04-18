package questionario.project.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import questionario.project.dto.QuizDTO;
import questionario.project.entita.Quiz;

@Mapper
public interface QuizMapper {
	
	QuizMapper INSTANCE = Mappers.getMapper(QuizMapper.class);
	
	QuizDTO toDTO(Quiz q);
	
	Quiz toEntity(QuizDTO qDTO);
	
	List<QuizDTO> toDTOList(List<Quiz> q);
	
	List<Quiz> toEntityList(List<Quiz> qDTO);

}
