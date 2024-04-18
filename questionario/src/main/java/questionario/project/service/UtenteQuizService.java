package questionario.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import questionario.project.mapper.UtenteQuizMapper;
import questionario.project.repository.UtenteQuizRepository;

@Service
public class UtenteQuizService {
	
	@Autowired
	UtenteQuizRepository uqr;
	
	@Autowired
	UtenteQuizMapper uqm;
	
}
