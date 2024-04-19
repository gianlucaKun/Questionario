package questionario.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import questionario.project.dto.UtenteQuizDomandaDTO;
import questionario.project.entita.UtenteQuizDomanda;
import questionario.project.mapper.UtenteQuizDomandaMapper;
import questionario.project.repository.DomandaRepository;
import questionario.project.repository.UtenteQuizDomandaRepository;
import questionario.project.repository.UtenteQuizRepository;

@Service
public class UtenteQuizDomandaService {
	
	@Autowired
	UtenteQuizDomandaRepository uqdr;
	
	@Autowired
	UtenteQuizDomandaMapper uqdm;
	
	//crud basic
	//add
	public void add(UtenteQuizDomandaDTO u) {
		uqdr.save(uqdm.utenteQuizDomandaDtoToEntity(u));
	}
	//select *all 
	public List<UtenteQuizDomandaDTO> selectAll(){
		return uqdm.utenteQuizDomandaToDtoList(uqdr.findAll());
	}
	//Select where id
	public UtenteQuizDomandaDTO selectById(Long id) {
		return uqdm.utenteQuizDomandaToDto(uqdr.findById(id).orElse(null));
	}
	@Autowired
	DomandaRepository dr;
	@Autowired
	UtenteQuizRepository uqr;
	//Update
	public UtenteQuizDomandaDTO update(Long id,UtenteQuizDomandaDTO ub ) {
		UtenteQuizDomanda u = uqdr.findById(id).orElse(null);
		u.setDoLater(ub.getDoLater() != null ? ub.getDoLater() : u.getDoLater());
		u.setPoints(ub.getPoints() != null ? ub.getPoints() : u.getPoints());
		u.setUtenteNote(ub.getUtenteNote() != null ? ub.getUtenteNote() : u.getUtenteNote());
		u.setTeacherNotes(ub.getTeacherNotes() != null ? ub.getTeacherNotes() : u.getTeacherNotes());
		u.setDomanda(ub.getDomandaId() != null ? dr.findById(ub.getDomandaId()).orElse(null) : u.getDomanda());
		u.setUtenteQuiz(ub.getUtenteQuizId() != null ? uqr.findById(ub.getUtenteQuizId()).orElse(null) : u.getUtenteQuiz());
		uqdr.save(u);
		return uqdm.utenteQuizDomandaToDto(u);
	}
	//delete
	public void delte(Long id) {
		uqdr.deleteById(id);
	}
	
}
