package br.com.phoebus.pandemicsystem.services;

import java.util.Date;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phoebus.pandemicsystem.domain.Hospital;
import br.com.phoebus.pandemicsystem.repositories.HospitalRepository;

@Service
public class HospitalService {

	@Autowired
	private HospitalRepository hospRepo;

	
	public Hospital saveHospital(Hospital hospital) {
		hospital.getOccupation().setDate(new Date());
		return hospRepo.save(hospital);
	}
	
	public Hospital getHospitalById(Integer id) {
		return hospRepo.findById(id).get();
	}
	
	public Iterable<Hospital> getAllHospitals() {
		return hospRepo.findAll();
	}
	
	/**
	 * Verifica a ocupaçao de todos os hospitais cadastrados e faz o calculo percentual
	 */
	public Double getHospitalsUpPercent() {
		Iterable<Hospital> hospitals = this.getAllHospitals();
		
		Long allHospitals = StreamSupport.stream(hospitals.spliterator(), false).count();
		Long upHospitals = StreamSupport.stream(hospitals.spliterator(), false)
				.filter(h -> 90 > h.getOccupation().getPercent()).count();

		return ((0.0 + allHospitals - upHospitals) / allHospitals) * 100.00;
	}
	
	public Double getPercentHospitalDown() {
		return 100.00 - this.getHospitalsUpPercent();
	}
	
	
	public Hospital getHospitalPercentMaxTime() {
		return hospRepo.getHospitalMaxPercentTime();
	}
	 
	public Hospital getHospitalPercentMinTime() {
		return hospRepo.getHospitalMinPercentTime();
	}
	
}
