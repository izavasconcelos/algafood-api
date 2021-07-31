package com.algafood.domain.kitchen.service.impl;

import com.algafood.domain.kitchen.entity.Kitchen;
import com.algafood.domain.kitchen.repository.KitchenRepository;
import com.algafood.domain.kitchen.service.KitchenService;
import com.algafood.infrastructure.common.exception.EntityUsedException;
import com.algafood.infrastructure.common.exception.KitchenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KitchenServiceImpl implements KitchenService {

	private final KitchenRepository kitchenRepository;

	@Override
	public Kitchen createKitchen(final Kitchen kitchen) {
		if(kitchenRepository.existsByName(kitchen.getName())) {
			throw new EntityUsedException("Kitchen Already Exists");
		}
		return kitchenRepository.save(kitchen);
	}

	@Override
	public Kitchen findById(final Long id) {
		return findKitchen(id);
	}

	@Override
	public void update(final Long id, final Kitchen newKitchen) {
		Kitchen kitchen = findKitchen(id);

		BeanUtils.copyProperties(newKitchen, kitchen, "id");
		kitchenRepository.save(kitchen);
	}

	@Override
	public List<Kitchen> findAll() {
		return kitchenRepository.findAll();
	}

	@Override
	public void delete(final Long id) {
		try {
			Kitchen kitchen = findKitchen(id);
			kitchenRepository.delete(kitchen);
		} catch (DataIntegrityViolationException ex) {
			throw new EntityUsedException(String.format("Entidade Id=%d em uso!", id));
		}
	}

	private Kitchen findKitchen(final Long id) {
		return kitchenRepository.findById(id)
				.orElseThrow(() -> new KitchenNotFoundException("Kitchen not found"));
	}
}
