package com.algafood.domain.city.service.impl;

import com.algafood.domain.city.entity.City;
import com.algafood.domain.city.repository.CityRepository;
import com.algafood.domain.city.service.CityService;
import com.algafood.domain.state.entity.State;
import com.algafood.domain.state.service.StateService;
import com.algafood.infrastructure.common.exception.CityNotFoundException;
import com.algafood.infrastructure.common.exception.EntityUsedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

	private final CityRepository cityRepository;

	private final StateService stateService;

	@Override
	public City createCity(City city) {
		State state = stateService.findById(city.getState().getId());

		if(cityRepository.existsByNameAndStateId(city.getName(), city.getState().getId())) {
			throw new EntityUsedException("City Already Exists");
		}

		city.setState(state);

		return cityRepository.save(city);
	}

	@Override
	public City findById(Long id) {
		return findCity(id);
	}

	@Override
	public List<City> findAll() {
		return cityRepository.findAll();
	}

	@Override
	public void update(Long id, City newCity) {
		City city = findCity(id);

		BeanUtils.copyProperties(newCity, city, "id");
		cityRepository.save(city);
	}

	@Override
	public void delete(Long id) {
		try {
			City city = findCity(id);
			cityRepository.delete(city);
		} catch (DataIntegrityViolationException ex) {
			throw new EntityUsedException(String.format("Entity id=%d can't be delete!", id));
		}
	}

	private City findCity(final Long id) {
		return cityRepository.findById(id)
				.orElseThrow(() -> new CityNotFoundException("City not found"));
	}
}
