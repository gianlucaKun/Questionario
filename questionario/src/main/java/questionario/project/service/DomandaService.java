package questionario.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import questionario.project.dto.DomandaDTO;
import questionario.project.entita.Domanda;
import questionario.project.mapper.DomandaMapper;
import questionario.project.repository.DomandaRepository;
import questionario.project.repository.RispostaRepository;

@Service
public class DomandaService {
	
	@Autowired
	DomandaRepository dr;
	
	@Autowired
	DomandaMapper dm;
	
	//crudEssenziali
	public void addNew(DomandaDTO d) {
		dr.save(dm.toEntity(d));
	}
	
	public DomandaDTO update(DomandaDTO dn, Long id) {
		Domanda d = dr.findById(id).orElse(null);
		d.setTesto(dn.getTesto() != null ? dn.getTesto() : d.getTesto());
		dr.save(d);
		return dm.toDTO(d);
	}
	
	@Autowired
	RispostaRepository rr;
	
	public void addRisposta(Long id, Long id_risposta) {
		Domanda d = dr.findById(id).orElse(null);
		d.getRisposta().add(rr.findById(id_risposta).orElse(null));
		dr.save(d);
	}
	
	public void rimuoviRisposta(Long id, Long id_risposta) {
		Domanda d = dr.findById(id).orElse(null);
		d.getRisposta().remove(rr.findById(id_risposta).orElse(null));
		dr.save(d);
	}
	
	public void delete(Long id) {
		dr.deleteById(id);
	}
	
	public List<DomandaDTO> getAll(){
		return dm.toDTOList(dr.findAll());
	}
	
	public DomandaDTO findById(Long id) {
		return dm.toDTO(dr.findById(id).orElse(null));
	}

	
}
