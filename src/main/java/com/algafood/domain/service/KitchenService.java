package com.algafood.domain.service;

import com.algafood.domain.entity.Kitchen;
import com.algafood.domain.exception.EntityUsedException;
import com.algafood.domain.exception.KitchenNotFoundException;
import com.algafood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KitchenService {

	@Autowired
	private KitchenRepository kitchenRepository;

	public Kitchen save(Kitchen kitchen) {
		return kitchenRepository.save(kitchen);
	}

	public void delete(Long id) {
		try {
			Optional<Kitchen> cozinha = kitchenRepository.findById(id);
			if (cozinha.isPresent()) {
				kitchenRepository.delete(cozinha.get());
			} else {
				throw new KitchenNotFoundException("Cozinha Não Encontrada!");
			}
		} catch (DataIntegrityViolationException ex) {
			throw new EntityUsedException(String.format("Entidade Id=%d em uso!", id));
		}
	}
}
