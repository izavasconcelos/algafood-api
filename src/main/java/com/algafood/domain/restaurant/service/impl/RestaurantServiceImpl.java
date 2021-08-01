package com.algafood.domain.restaurant.service.impl;

import com.algafood.domain.kitchen.entity.Kitchen;
import com.algafood.domain.kitchen.service.KitchenService;
import com.algafood.domain.restaurant.entity.Restaurant;
import com.algafood.domain.restaurant.repository.RestaurantRepository;
import com.algafood.domain.restaurant.service.RestaurantService;
import com.algafood.infrastructure.common.exception.EntityUsedException;
import com.algafood.infrastructure.common.exception.RestaurantNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

	private final RestaurantRepository restaurantRepository;

	private final KitchenService kitchenService;

	@Override
	public Restaurant createRestaurant(final Restaurant restaurant) {
		Kitchen kitchen = kitchenService.findById(restaurant.getKitchen().getId());

		if(restaurantRepository.existsByNameAndKitchenId(restaurant.getName(), restaurant.getKitchen().getId())) {
			throw new EntityUsedException("Restaurant Already Exists");
		}
		restaurant.setKitchen(kitchen);

		return restaurantRepository.save(restaurant);
	}

	@Override
	public Restaurant findById(final Long id) {
		return findRestaurant(id);
	}

	@Override
	public List<Restaurant> findAll() {
		return restaurantRepository.findAll();
	}

	@Override
	public void update(final Long id, final Restaurant newRestaurant) {
		Restaurant restaurant = findRestaurant(id);

		BeanUtils.copyProperties(newRestaurant, restaurant, "id", "paymentType", "address", "createdAt", "products");
		restaurantRepository.save(restaurant);
	}

	@Override
	public void updatePart(final Long id, final Map<String, Object> fields) {
		Restaurant restaurant = findRestaurant(id);

		merge(fields, restaurant);
		restaurantRepository.save(restaurant);
	}

	@Override
	public void delete(final Long id) {
		try {
			Restaurant restaurant = findRestaurant(id);
			restaurantRepository.delete(restaurant);
		} catch (DataIntegrityViolationException ex) {
			throw new EntityUsedException(String.format("Entidade Id=%d em uso!", id));
		}
	}

	private Restaurant findRestaurant(final Long id) {
		return restaurantRepository.findById(id)
				.orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));
	}

	private void merge(Map<String, Object> restaurantFields, Restaurant restaurant) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurant newRestaurant = objectMapper.convertValue(restaurantFields, Restaurant.class);

		restaurantFields.forEach((name, value) -> {
			Field field = ReflectionUtils.findField(Restaurant.class, name);
			field.setAccessible(true);
			Object newValue = ReflectionUtils.getField(field, newRestaurant);
			ReflectionUtils.setField(field, restaurant, newValue);
		});
	}
}
