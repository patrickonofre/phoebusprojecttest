package br.com.phoebus.pandemicsystem.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.phoebus.pandemicsystem.domain.Hospital;

public interface HospitalRepository extends CrudRepository<Hospital, Integer> {

	@Query(value = "SELECT h.id, h.address, h.cnpj, h.coordinates, h.name FROM hospital AS h "
			+ "JOIN occupation AS o ON h.id = o.hospital_id WHERE o.date = (SELECT MIN(date) "
			+ "FROM occupation WHERE percent > 90)"
			, nativeQuery = true)
	public Hospital getHospitalMaxPercentTime();
	
	@Query(value = "SELECT h.id, h.address, h.cnpj, h.coordinates, h.name FROM hospital AS h "
			+ "JOIN occupation AS o ON h.id = o.hospital_id WHERE o.date = (SELECT MAX(date) "
			+ "FROM occupation WHERE percent > 90)"
			, nativeQuery = true)
	public Hospital getHospitalMinPercentTime();
}
