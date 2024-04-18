package questionario.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import questionario.project.mapper.UtenteMapper;
import questionario.project.repository.UtenteRepository;

@Service
public class UtenteService {
	
	@Autowired
	UtenteRepository ur;
	
	@Autowired
	UtenteMapper um;

}
