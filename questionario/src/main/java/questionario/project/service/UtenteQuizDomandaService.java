package questionario.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import questionario.project.mapper.UtenteQuizDomandaMapper;
import questionario.project.repository.UtenteQuizDomandaRepository;

@Service
public class UtenteQuizDomandaService {
	
	@Autowired
	UtenteQuizDomandaRepository uqdr;
	
	@Autowired
	UtenteQuizDomandaMapper uqdm;
	
}
