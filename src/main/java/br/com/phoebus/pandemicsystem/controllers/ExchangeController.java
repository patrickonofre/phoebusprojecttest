package br.com.phoebus.pandemicsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.phoebus.pandemicsystem.domain.Exchange;
import br.com.phoebus.pandemicsystem.services.ExchangeService;

/**
 *	Controller relativo ao intercâmbio de recursos
 */
@RestController
@RequestMapping("/exchange")
public class ExchangeController {

	@Autowired
	private ExchangeService exServ;
	
	/**
	 * retorna todas os intercambios. Devido a não implementação atual do padrão DAO, o JSON de retorno, está retornando
	 * todos os dados relativos ao hospital, além dos recursos trocados. 
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<Iterable<Exchange>> allExchanges() {
		return new ResponseEntity<Iterable<Exchange>>(exServ.allExchanges(), HttpStatus.OK);
	}
	
	/**
	 * Endpoint que recebe uma lista de intercambios e passa para o service fazer a validação 
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody ResponseEntity<Iterable<Exchange>> saveExchange(@RequestBody List<Exchange> exchanges) {
		Iterable<Exchange> exchangesSave = exServ.saveExchange(exchanges);
		if(exchangesSave != null) {
			return new ResponseEntity<Iterable<Exchange>>(exchangesSave, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Iterable<Exchange>>(HttpStatus.NO_CONTENT);
		}
	}
}
