package questionario.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import questionario.project.dto.RispostaDTO;
import questionario.project.entita.Risposta;
import questionario.project.mapper.RispostaMapper;
import questionario.project.repository.DomandaRepository;
import questionario.project.repository.RispostaRepository;

@Service
public class RispostaService {
	
	@Autowired
	RispostaRepository rr;
	
	@Autowired
	RispostaMapper rm;
	
	//crudBasilari
	public void addNew(RispostaDTO r) {
		rr.save(rm.toEntity(r));
	}
	@Autowired
	DomandaRepository dr;
	
	public RispostaDTO update(RispostaDTO rn, Long id) {
		Risposta r = rr.findById(id).orElse(null);
		r.setCorretta(rn.getCorretta() != null ? rn.getCorretta() : r.getCorretta());
		r.setTesto(rn.getTesto() != null ? rn.getTesto() : r.getTesto());
		r.setDomanda(rn.getDomanda() != null ? dr.findById(rn.getDomanda().getId()).orElse(null) : r.getDomanda());
		rr.save(r);
		return rm.toDTO(r);
	}
	
	public void delete(Long id) {
		rr.deleteById(id);
	}
	
	public RispostaDTO getByID(Long id) {
		return rm.toDTO(rr.findById(id).orElse(null));
	}
	
	public List<RispostaDTO> getAll(){
		return rm.toDTOList(rr.findAll());
	}
	
}
