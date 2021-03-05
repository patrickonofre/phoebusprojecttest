package br.com.phoebus.pandemicsystem;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.phoebus.pandemicsystem.domain.Exchange;
import br.com.phoebus.pandemicsystem.domain.Hospital;
import br.com.phoebus.pandemicsystem.domain.Occupation;
import br.com.phoebus.pandemicsystem.domain.Resource;
import br.com.phoebus.pandemicsystem.services.ExchangeService;
import br.com.phoebus.pandemicsystem.services.HospitalService;

/**
 *
 *	Aplicada todas as funcionalidades reportadas nos requisitos. Devido ao tempo não pude implementar algumas idéias, como os DAO, 
 *para ter mais controle sobre os dados de entrada e de saída. Tentei dividir a app no padrão MVC, mas como é um projeto curto 
 *tentei fazer o mais simples possível. Existem pontos de refatoração, mas quem sabe em uma segunda versão. Devido ao tempo
 *não consegui fazer a implementação dos testes.
 *
 *Qualquer dúvida, estou a disposição.
 */
@SpringBootApplication
public class PandemicsystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PandemicsystemApplication.class, args);
	}

	
	
	
	/*
	* Geração de alguns dados apenas para povoar a base de dados. 
	*/
	@Autowired
	private HospitalService hospService;
	
	@Autowired
	private ExchangeService exServ;
	
	@Override
	public void run(String... args) throws Exception {
		
		Hospital hospital1 = new Hospital(null, "HNSN", "Av Pedro II", "123450001230", "-78987, 987654", 
				Arrays.asList("32442309", "987768765"), null, null);
		
		Hospital hospital2 = new Hospital(null, "Unimed", "Av Pedro II", "123450001230", "-78987, 987654", 
				Arrays.asList("32442309", "987768765"), null, null);
		
		Resource resource1 = new Resource(null, "Enfermeiro", 3, hospital1);
		Resource resource2 = new Resource(null, "Médico", 3, hospital1);
		Resource resource3 = new Resource(null, "Respirador", 6, hospital1);
		Resource resource4 = new Resource(null, "Ambulância", 10, hospital1);
		Resource resource5 = new Resource(null, "Tomógrafo", 12, hospital1);
		Resource resource6 = new Resource(null, "Enfermeiro", 3, hospital2);
		Resource resource7 = new Resource(null, "Médico", 3, hospital2);
		Resource resource8 = new Resource(null, "Respirador", 6, hospital2);
		Resource resource9 = new Resource(null, "Ambulância", 10, hospital2);
		Resource resource10 = new Resource(null, "Tomógrafo", 12, hospital2);
		
		
		hospital1.setResouces(Arrays.asList(resource1, resource2, resource3, resource4, resource5));
		hospital2.setResouces(Arrays.asList(resource6, resource7, resource8, resource9, resource10));
		
		Occupation occupation1 = new Occupation(92.3, hospital1);
		Occupation occupation2 = new Occupation(92.3, hospital2);
		
		hospital1.setOccupation(occupation1);
		hospital2.setOccupation(occupation2);
		
		hospService.saveHospital(hospital1);
		hospService.saveHospital(hospital2);
		
		Exchange ex1 = new Exchange(hospital2, Arrays.asList(resource1));
		Exchange ex2 = new Exchange(hospital1, Arrays.asList(resource10));
		exServ.saveExchange(Arrays.asList(ex1, ex2));	
	}
}
