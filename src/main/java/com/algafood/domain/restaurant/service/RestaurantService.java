package com.algafood.domain.restaurant.service;

import com.algafood.domain.restaurant.entity.Restaurant;

import java.util.List;
import java.util.Map;

public interface RestaurantService {

	Restaurant createRestaurant(final Restaurant restaurant);

	Restaurant findById(final Long id);

	List<Restaurant> findAll();

	void update(final Long id, final Restaurant restaurant);

	void updatePart(final Long id, final Map<String, Object> fields);

	void delete(final Long id);
}
