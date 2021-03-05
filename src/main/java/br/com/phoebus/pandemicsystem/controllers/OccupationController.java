package br.com.phoebus.pandemicsystem.controllers;

import java.util.Date;

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

import br.com.phoebus.pandemicsystem.domain.Occupation;
import br.com.phoebus.pandemicsystem.services.OccupationService;

/**
 *	Controller relativo a entidade que controla a ocupação nos Hospitais.
 */
@RestController
@RequestMapping(value = "/occupation")
public class OccupationController {

	@Autowired
	private OccupationService occServ;
	
	/**
	 *	Endpoint retorna a ocupação do hospital passado por id na requisição 
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Occupation> findOccupationByHospitalId(@PathVariable Integer id) {
		return new ResponseEntity<Occupation>(occServ.getOccupationByHospitalId(id), HttpStatus.OK);
	}
	
	/**
	 * 	Endpoint para atualizar a ocupação do hospital, na requisição necessita apenas do id do hospital
	 * e valor percentual para ser atualizado no JSON.
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody ResponseEntity<Occupation> updateOccupation(@RequestBody Occupation occupation) {
		occupation.setDate(new Date());
		return new ResponseEntity<Occupation>(occServ.saveOccupation(occupation), HttpStatus.CREATED);
	}

}
