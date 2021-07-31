package com.algafood.domain.service;

import com.algafood.domain.entity.Kitchen;
import com.algafood.domain.entity.Restaurant;
import com.algafood.domain.exception.EntityUsedException;
import com.algafood.domain.exception.RestaurantBadRequestException;
import com.algafood.domain.exception.RestaurantNotFoundException;
import com.algafood.domain.repository.KitchenRepository;
import com.algafood.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

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
