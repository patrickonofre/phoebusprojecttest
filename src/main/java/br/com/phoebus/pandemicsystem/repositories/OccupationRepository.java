package br.com.phoebus.pandemicsystem.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.phoebus.pandemicsystem.domain.Occupation;

public interface OccupationRepository extends CrudRepository<Occupation, Integer> {
	
	@Query(value = "SELECT * FROM occupation WHERE hospital_id = ?"
			, nativeQuery = true)
	public Occupation getOccupationByHospitalId(Integer id);
}
