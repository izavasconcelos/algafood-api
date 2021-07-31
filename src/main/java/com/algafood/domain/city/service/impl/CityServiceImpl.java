package com.algafood.domain.city.service.impl;

import com.algafood.domain.city.entity.City;
import com.algafood.domain.state.entity.State;
import com.algafood.infrastructure.common.exception.CityBadRequestException;
import com.algafood.infrastructure.common.exception.CityNotFoundException;
import com.algafood.infrastructure.common.exception.EntityUsedException;
import com.algafood.domain.city.repository.CityRepository;
import com.algafood.domain.state.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityServiceImpl {

	private final CityRepository cityRepository;

	private final StateRepository stateRepository;

	public City save(City city) {
		State state = stateRepository.findById(city.getState().getId())
				.orElseThrow(() -> new CityBadRequestException("Estado Não Existe"));
		city.setState(state);

		return cityRepository.save(city);
	}

	public void delete(Long id) {
		try {
			Optional<City> cidade = cityRepository.findById(id);
			if (cidade.isPresent()) {
				cityRepository.delete(cidade.get());
			} else {
				throw new CityNotFoundException("Cidade Não Encontrada!");
			}
		} catch (DataIntegrityViolationException ex) {
			throw new EntityUsedException(String.format("Entidade Id=%d em uso!", id));
		}
	}
}
