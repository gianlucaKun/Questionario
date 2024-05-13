package questionario.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import questionario.project.dto.UtenteQuizDomandaRispostaDTO;
import questionario.project.entita.UtenteQuizDomandaRisposta;
import questionario.project.mapper.UtenteQuizDomandaRispostaMapper;
import questionario.project.repository.RispostaRepository;
import questionario.project.repository.UtenteQuizDomandaRepository;
import questionario.project.repository.UtenteQuizDomandaRispostaRepository;

@Service
public class UtenteQuizDomandaRispostaService {
	
	@Autowired
	UtenteQuizDomandaRispostaRepository  uqdrr;
	
	@Autowired
	UtenteQuizDomandaRispostaMapper uqdrm;
	//crud basilari
	//add
	public UtenteQuizDomandaRispostaDTO add(UtenteQuizDomandaRispostaDTO u) {
		UtenteQuizDomandaRisposta newRecord =  uqdrr.save(uqdrm.utenteQuizDomandaRispostaDtoToEntity(u));
		return uqdrm.utenteQuizDomandaRispostaToDto(newRecord);
	}
	//select * all
	public List<UtenteQuizDomandaRispostaDTO> seletcAll(){
		return uqdrm.utenteQuizDomandaRispostaToDtoList(uqdrr.findAll());
	}
	//select where id
	public UtenteQuizDomandaRispostaDTO selectById(Long id) {
		return uqdrm.utenteQuizDomandaRispostaToDto(uqdrr.findById(id).orElse(null));
	}
	@Autowired
	RispostaRepository rr;
	@Autowired
	UtenteQuizDomandaRepository uqdr;
	//update
	public UtenteQuizDomandaRispostaDTO update(UtenteQuizDomandaRispostaDTO rb, Long id) {
		UtenteQuizDomandaRisposta r = uqdrr.findById(id).orElse(null);
		r.setChecked(rb.getChecked() != null ? rb.getChecked() : r.getChecked());
		r.setRisposta(rb.getRispostaId() != null ? rr.findById(rb.getRispostaId()).orElse(null) : r.getRisposta());
		r.setUtenteQuizDomanda(rb.getUtenteQuizDomandaId() != null ? uqdr.findById(rb.getUtenteQuizDomandaId()).orElse(null) : r.getUtenteQuizDomanda());
		uqdrr.save(r);
		return uqdrm.utenteQuizDomandaRispostaToDto(r);
	}
	
	//delete
	public void delete(Long id) {
		uqdrr.deleteById(id);
	}
}
