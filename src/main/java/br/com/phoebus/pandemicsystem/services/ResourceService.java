package br.com.phoebus.pandemicsystem.services;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phoebus.pandemicsystem.domain.Resource;
import br.com.phoebus.pandemicsystem.repositories.ResourceRepository;

@Service
public class ResourceService {

	@Autowired
	private ResourceRepository resRepo;
	
	@Autowired
	private HospitalService hospServ;
	
	public Resource saveResource(Resource resource) {
		return resRepo.save(resource);
	}
	
	public Resource getResourceById(Integer id) {
		return resRepo.findById(id).get();
	}
	
	public Iterable<Resource> getAllResources() {
		return resRepo.findAll();
	}
	
	public Integer getAmountResources(String name) {
		return resRepo.getAmountResourcesByName(name);
	}
	
	/**
	 *Retorna um map com o nome do recurso e quantidade do mesmo.
	 */
	private Map<String, Integer> getSumAllResoursesHospitals() { 
		Map<String, Integer> mapResources = new HashMap<String, Integer>();
		
		for(Resource res: this.getAllResources()) {
			if(!mapResources.containsKey(res.getName())) {
				mapResources.put(res.getName(), this.getAmountResources(res.getName()));
			}
		}
		return mapResources;
	}
	
	/**
	 *	A partir do retor do método getSumAllResoursesHospitals() faz o calculo da média dos recursos para 
	 *todos os hospitais.
	 */
	public Map<String, Double> getAvgAllResoursesHospitals() {
		
		Map<String, Double> mapResources = new HashMap<String, Double>();
		
		this.getSumAllResoursesHospitals().forEach((name, value) -> {
			mapResources.put(name, (value + 0.0) / StreamSupport.stream(hospServ.getAllHospitals().spliterator(), false).count());
		});
		return mapResources;
	}
	
}
