package com.algafood.domain.city.service;

import com.algafood.domain.city.entity.City;

import java.util.List;

public interface CityService {

	City createCity(final City city);

	City findById(final Long id);

	List<City> findAll();

	void update(final Long id, final City city);

	void delete(final Long id);
}
