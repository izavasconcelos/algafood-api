package com.algafood.domain.state.repository;

import com.algafood.domain.state.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

	boolean existsByName(final String name);
}
