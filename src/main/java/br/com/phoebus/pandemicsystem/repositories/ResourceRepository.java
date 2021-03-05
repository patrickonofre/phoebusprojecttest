package br.com.phoebus.pandemicsystem.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.phoebus.pandemicsystem.domain.Resource;

public interface ResourceRepository extends CrudRepository<Resource, Integer> {

	@Query(value = "SELECT COUNT(name) FROM resource WHERE name = ?"
			, nativeQuery = true)
	public Integer getAmountResourcesByName(String resource);
}
