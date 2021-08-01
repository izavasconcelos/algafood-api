package com.algafood.domain.state.service.impl;

import com.algafood.domain.state.entity.State;
import com.algafood.domain.state.repository.StateRepository;
import com.algafood.domain.state.service.StateService;
import com.algafood.infrastructure.common.exception.EntityUsedException;
import com.algafood.infrastructure.common.exception.StateNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {

	private final StateRepository stateRepository;

	@Override
	public State findById(Long id) {
		return findState(id);
	}

	@Override
	public List<State> findAll() {
		return stateRepository.findAll();
	}

	@Override
	public State createState(State state) {
		if(stateRepository.existsByName(state.getName())) {
			throw new EntityUsedException("Entity Already Exists!");
		}

		return stateRepository.save(state);
	}

	@Override
	public void update(Long id, State newState) {
		State state = findState(id);

		BeanUtils.copyProperties(newState, state, "id");
		stateRepository.save(state);
	}

	public void delete(Long id) {
		try {
			State state = findState(id);
			stateRepository.delete(state);
		} catch (DataIntegrityViolationException ex) {
			throw new EntityUsedException(String.format("Entity Id=%d can't be delete!", id));
		}
	}

	private State findState(final Long id) {
		return stateRepository.findById(id)
				.orElseThrow(() -> new StateNotFoundException("State not found"));
	}
}
