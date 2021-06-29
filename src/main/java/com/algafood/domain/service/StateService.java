package com.algafood.domain.service;

import com.algafood.domain.entity.State;
import com.algafood.domain.exception.EntityUsedException;
import com.algafood.domain.exception.StateNotFoundException;
import com.algafood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;

	public State save(State state) {
		return stateRepository.save(state);
	}

	public void delete(Long id) {
		try {
			Optional<State> estado = stateRepository.findById(id);
			if (estado.isPresent()) {
				stateRepository.delete(estado.get());
			} else {
				throw new StateNotFoundException("Estado Não Encontrado!");
			}
		} catch (DataIntegrityViolationException ex) {
			throw new EntityUsedException(String.format("Entidade Id=%d em uso!", id));
		}
	}
}
