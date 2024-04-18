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
	
	//crud basilare
	//add
	public void add(RispostaDTO r) {
		rr.save(rm.toEntity(r));
	}
	//select*all
	public List<RispostaDTO>  selectAll() {
		return rm.toDTOList(rr.findAll());
	}
	//select where id
	public RispostaDTO selectById(Long id) {
		return rm.toDTO(rr.findById(id).orElse(null));
	}
	@Autowired
	DomandaRepository dr;
	//update
	public RispostaDTO update(Long id, RispostaDTO rb) {
		Risposta r = rr.findById(id).orElse(null);
		r.setTesto(rb.getTesto() != null ? rb.getTesto() : r.getTesto());
		r.setCorretta(rb.getCorretta() != null ? rb.getCorretta() : r.getCorretta());
		r.setDomanda(dr.findById(rb.getDomandaId()) != null ? dr.findById(rb.getDomandaId()).orElse(null) : r.getDomanda());
		rr.save(r);
		return rm.toDTO(r);
	}
	//delete
	public void delete(Long id) {
		rr.deleteById(id);
	}
}
