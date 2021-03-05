package br.com.phoebus.pandemicsystem.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.phoebus.pandemicsystem.domain.Exchange;

public interface ExchangeRepository extends CrudRepository<Exchange, Integer> {


}
