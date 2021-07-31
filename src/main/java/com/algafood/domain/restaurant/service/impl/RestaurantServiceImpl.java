package com.algafood.domain.restaurant.service.impl;

import com.algafood.domain.kitchen.entity.Kitchen;
import com.algafood.domain.restaurant.entity.Restaurant;
import com.algafood.infrastructure.common.exception.EntityUsedException;
import com.algafood.infrastructure.common.exception.RestaurantBadRequestException;
import com.algafood.infrastructure.common.exception.RestaurantNotFoundException;
import com.algafood.domain.kitchen.repository.KitchenRepository;
import com.algafood.domain.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl {

	private final RestaurantRepository restaurantRepository;

	private final KitchenRepository kitchenRepository;

	public Restaurant save(Restaurant restaurant) {
		Kitchen kitchen = kitchenRepository.findById(restaurant.getKitchen().getId())
				.orElseThrow(() -> new RestaurantBadRequestException("Cozinha Não Existe"));
		restaurant.setKitchen(kitchen);

		return restaurantRepository.save(restaurant);
	}

	public void delete(Long id) {
		try {
			Optional<Restaurant> restaurante = restaurantRepository.findById(id);
			if (restaurante.isPresent()) {
				restaurantRepository.delete(restaurante.get());
			} else {
				throw new RestaurantNotFoundException("Restaurante Não Encontrado!");
			}
		} catch (DataIntegrityViolationException ex) {
			throw new EntityUsedException(String.format("Entidade Id=%d em uso!", id));
		}
	}
}
