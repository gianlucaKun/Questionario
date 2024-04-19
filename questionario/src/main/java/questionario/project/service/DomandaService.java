package questionario.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import questionario.project.dto.DomandaDTO;
import questionario.project.dto.proiezione.DomandaProiezione;
import questionario.project.entita.Domanda;
import questionario.project.mapper.DomandaMapper;
import questionario.project.mapper.RispostaMapper;
import questionario.project.repository.DomandaRepository;
import questionario.project.repository.RispostaRepository;

@Service
public class DomandaService {
	
	@Autowired
	DomandaRepository dr;
	
	@Autowired
	DomandaMapper dm;
	
	//crud basi
	//add
	public void add(DomandaDTO d) {
		dr.save(dm.toEntity(d));
	}
	//select * all
	public List<DomandaDTO> selectAll() {
		return dm.toDTOList(dr.findAll());
	}
	//select where id
	public DomandaDTO selectById(Long id) {
		return dm.toDTO(dr.findById(id).orElse(null));
	}
	//update
	public DomandaDTO update(DomandaDTO db, Long id) {
		Domanda d = dr.findById(id).orElse(null);
		d.setTesto(db.getTesto() != null ? db.getTesto() : d.getTesto());
		dr.save(d);
		return dm.toDTO(d);
	}
	//delete
	public void delete(Long id) {
		dr.deleteById(id);
	}
	
	@Autowired
	RispostaRepository rr;
	
	@Autowired
	RispostaMapper rm;
	
    public DomandaProiezione getRisposte(Long domandaId) {
    	DomandaProiezione d = new DomandaProiezione();
    	d.setId(domandaId);
    	d.setDomandaTesto(dr.findById(domandaId).orElse(null).getTesto());
    	d.setListaRisposte(rm.toDTOList(rr.findByDomandaId(domandaId)));
		return d;
    }
    
}
