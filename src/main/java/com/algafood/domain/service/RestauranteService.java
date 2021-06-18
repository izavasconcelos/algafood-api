package com.algafood.domain.service;

import com.algafood.domain.entity.Cozinha;
import com.algafood.domain.entity.Restaurante;
import com.algafood.domain.exception.EntityUsedException;
import com.algafood.domain.exception.RestaurantBadRequestException;
import com.algafood.domain.exception.RestaurantNotFoundException;
import com.algafood.domain.repository.CozinhaRepository;
import com.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Restaurante save(Restaurante restaurante) {
		Cozinha cozinha = cozinhaRepository.getById(restaurante.getCozinha().getId());
		if(cozinha != null) {
			return restauranteRepository.save(restaurante);
		} else {
			throw new RestaurantBadRequestException("Cozinha Não Existe");
		}
	}

	public void delete(Long id) {
		try {
			Restaurante restaurante = restauranteRepository.getById(id);
			if (restaurante != null) {
				restauranteRepository.delete(restaurante);
			} else {
				throw new RestaurantNotFoundException("Restaurante Não Encontrado!");
			}
		} catch (DataIntegrityViolationException ex) {
			throw new EntityUsedException(String.format("Entidade Id=%d em uso!", id));
		}
	}
}
