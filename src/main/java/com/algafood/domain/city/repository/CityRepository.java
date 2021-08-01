package com.algafood.domain.city.repository;

import com.algafood.domain.city.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	boolean existsByNameAndStateId(final String name, final Long id);
}
