package com.algafood.domain.service;

import com.algafood.domain.entity.City;
import com.algafood.domain.entity.State;
import com.algafood.domain.exception.CityBadRequestException;
import com.algafood.domain.exception.CityNotFoundException;
import com.algafood.domain.exception.EntityUsedException;
import com.algafood.domain.repository.CityRepository;
import com.algafood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StateRepository stateRepository;

	public City save(City city) {
		State state = stateRepository.findById(city.getState().getId())
				.orElseThrow(() -> new CityBadRequestException("Estado Não Existe"));
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
