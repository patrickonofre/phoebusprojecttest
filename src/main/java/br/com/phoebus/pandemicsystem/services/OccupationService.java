package br.com.phoebus.pandemicsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phoebus.pandemicsystem.domain.Occupation;
import br.com.phoebus.pandemicsystem.repositories.OccupationRepository;

@Service
public class OccupationService {

	@Autowired
	private OccupationRepository occRepo;
	
	
	public Occupation saveOccupation(Occupation ocupation) {
		return occRepo.save(ocupation);
	}
	
	public Occupation getOccupationByHospitalId(Integer id) {
		return occRepo.getOccupationByHospitalId(id);
	}
	
	public Iterable<Occupation> getAllOccupation() {
		return occRepo.findAll();
	}
}
