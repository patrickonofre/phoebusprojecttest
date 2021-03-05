package br.com.phoebus.pandemicsystem.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.phoebus.pandemicsystem.services.ResourceService;

/**
 * Controller relativo a entidade de recursos. Como a aplicação não pode salvar, nem deletar novos recursor, implementado apenas
 * o end point que retorna a média de cada recurso para todos os hospitais.
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {
	
	@Autowired
	private ResourceService resServ;

	@GetMapping(value = "/avg")
	public ResponseEntity<Map<String, Double>> avgResourcesHospitals() {
		return new ResponseEntity<Map<String, Double>>(resServ.getAvgAllResoursesHospitals(), HttpStatus.OK);
	}
}
