package br.com.phoebus.pandemicsystem.services;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phoebus.pandemicsystem.domain.Exchange;
import br.com.phoebus.pandemicsystem.domain.Resource;
import br.com.phoebus.pandemicsystem.repositories.ExchangeRepository;

@Service
public class ExchangeService {

	@Autowired
	private ExchangeRepository exRepo;
	
	@Autowired
	private ResourceService resServ;
	
	@Autowired
	private HospitalService hospServ;
	
	public Iterable<Exchange> allExchanges() {
		return exRepo.findAll();
	}
	
	/**
	 * Applicação da lógica do intercâmbio de recursos e atualização do novo hospital para cada recurso.
	 */
	public Iterable<Exchange> saveExchange(List<Exchange> exchanges) {

		if(this.validateExchange(exchanges) || this.validatePercentHospital(exchanges)) {
			for(Exchange ex: exchanges) {
				for(Resource r: ex.getResources()) {
					r = resServ.getResourceById(r.getId());
					r.setHospital(ex.getHospital());
					resServ.saveResource(r);
				}
				ex.setDate(new Date());
			}
			
			return exRepo.saveAll(exchanges);
		} else {
			return null;
		}
	}
	
	/**
	 * Método auxiliar para validação da regra dos scores que deve ser iguais para concretizar o intecâmbio
	 */
	private Boolean validateExchange(List<Exchange> exchanges) {
		Set<Integer> sum = new HashSet<Integer>();
		
		for(Exchange ex: exchanges) {
			sum.add(this.sumScore(ex.getResources()));
		}
		
		return sum.size() == 1 ? true : false;
	}
	
	/**
	 * Método auxiliar para somar o score dos recursos 
	 */
	private Integer sumScore(List<Resource> resources) {
		int sum = 0;
		for(Resource r: resources) {
			r = resServ.getResourceById(r.getId());
			sum += r.getScore();
		}
		return sum;
	}
	
	/**
	 * Método auxiliar para fazer a validação da regra do percentual do hospital para realizar o intercâmbio
	 * Se ele achar algum hospital acima dos 90% já retorna true, assim podendo realizar o intercambio
	 * 
	 */
	private Boolean validatePercentHospital(List<Exchange> exchanges) {
		for(Exchange ex: exchanges) {
			ex.setHospital(hospServ.getHospitalById(ex.getHospital().getId()));
			if(ex.getHospital().getOccupation().getPercent() > 90) {
				return true;
			}
		}
		return false;
	}
}
