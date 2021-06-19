package com.algafood.domain.service;

import com.algafood.domain.entity.Restaurante;
import com.algafood.domain.exception.EntityUsedException;
import com.algafood.domain.exception.RestaurantBadRequestException;
import com.algafood.domain.exception.RestaurantNotFoundException;
import com.algafood.domain.repository.CozinhaRepository;
import com.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Restaurante save(Restaurante restaurante) {
		cozinhaRepository.findById(restaurante.getCozinha().getId())
				.orElseThrow(() -> new RestaurantBadRequestException("Cozinha Não Existe"));
		return restauranteRepository.save(restaurante);
	}

	public void delete(Long id) {
		try {
			Optional<Restaurante> restaurante = restauranteRepository.findById(id);
			if (restaurante.isPresent()) {
				restauranteRepository.delete(restaurante.get());
			} else {
				throw new RestaurantNotFoundException("Restaurante Não Encontrado!");
			}
		} catch (DataIntegrityViolationException ex) {
			throw new EntityUsedException(String.format("Entidade Id=%d em uso!", id));
		}
	}
}
