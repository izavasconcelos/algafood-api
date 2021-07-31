package com.algafood.domain.kitchen.service;

import com.algafood.domain.kitchen.entity.Kitchen;

import java.util.List;

public interface KitchenService {

	Kitchen createKitchen(final Kitchen kitchen);

	Kitchen findById(final Long id);

	List<Kitchen> findAll();

	void update(final Long id, final Kitchen kitchen);

	void delete(final Long id);

}
