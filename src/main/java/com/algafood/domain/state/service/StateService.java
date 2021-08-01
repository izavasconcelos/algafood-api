package com.algafood.domain.state.service;

import com.algafood.domain.state.entity.State;

import java.util.List;

public interface StateService {

	State createState(final State state);

	State findById(final Long id);

	List<State> findAll();

	void update(final Long id, final State state);

	void delete(final Long id);
}
