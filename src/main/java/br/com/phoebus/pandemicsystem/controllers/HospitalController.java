package br.com.phoebus.pandemicsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.phoebus.pandemicsystem.domain.Hospital;
import br.com.phoebus.pandemicsystem.services.HospitalService;

/**
 *	Controller relativo a entidade hospital e seus respectivos relatorios
 */
@RestController
@RequestMapping(value = "/hospital")
public class HospitalController {

	@Autowired
	private HospitalService hospService;
	
	/**
	 * Retorna os dados do hospital passado o id na requisição 
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Hospital> findHospitalById(@PathVariable Integer id) {
		return new ResponseEntity<Hospital>(hospService.getHospitalById(id), HttpStatus.OK);
	}
	
	/**
	 * salva o hospital e todos seus atributos 
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody ResponseEntity<Hospital> saveHospital(@RequestBody Hospital hospital) {
		return new ResponseEntity<Hospital>(hospService.saveHospital(hospital), HttpStatus.CREATED);
	}

	/**
	 * retorna o relatório do percentual de hospitais com ocupação acima de 90% 
	 */
	@GetMapping(value = "/report/90up")
	public ResponseEntity<Double> hospitalUpPercent() {
		return new ResponseEntity<Double>(hospService.getHospitalsUpPercent(), HttpStatus.OK);
	}
	
	/**
	 * retorna o relatório do percentual de hospitais com ocupação abaixo de 90% 
	 */
	@GetMapping(value = "/report/90down")
	public ResponseEntity<Double> hospitalDownPercent() {
		return new ResponseEntity<Double>(hospService.getPercentHospitalDown(), HttpStatus.OK);
	}
	
	/**
	 * retorna o relatório do hospital a mais tempo com ocupação acima de 90% 
	 */
	@GetMapping(value = "/report/maxtime")
	public Hospital hospitalMaxTimeUp() {
		return hospService.getHospitalPercentMaxTime();
	}
	
	/**
	 * Retorna p relatório dp hospital a menos tempo com ocupçao acima dos 90%
	 */
	@GetMapping(value = "/report/mintime")
	public Hospital hospitalMinTimeUp() {
		return hospService.getHospitalPercentMinTime();
	}
}
