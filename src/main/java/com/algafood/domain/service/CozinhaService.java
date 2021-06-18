package com.algafood.domain.service;

import com.algafood.domain.entity.Cozinha;
import com.algafood.domain.exception.EntityUsedException;
import com.algafood.domain.exception.KitchenNotFoundException;
import com.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Cozinha save(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	public void delete(Long id) {
		try {
			Cozinha cozinha = cozinhaRepository.getById(id);
			if (cozinha != null) {
				cozinhaRepository.delete(cozinha);
			} else {
				throw new KitchenNotFoundException("Cozinha Não Encontrada!");
			}
		} catch (DataIntegrityViolationException ex) {
			throw new EntityUsedException(String.format("Entidade Id=%d em uso!", id));
		}
	}
}
