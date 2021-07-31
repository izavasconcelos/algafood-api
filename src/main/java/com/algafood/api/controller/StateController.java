package com.algafood.api.controller;

import com.algafood.domain.entity.State;
import com.algafood.domain.repository.StateRepository;
import com.algafood.domain.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estados")
public class StateController {
	
    private final StateRepository stateRepository;

	private final StateService stateService;

    @GetMapping
    public ResponseEntity<List<State>> findAll() {
        return ResponseEntity.ok(stateRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> findById(@PathVariable Long id) {
		Optional<State> estado = stateRepository.findById(id);
        return estado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

	@PostMapping
	public ResponseEntity<State> save(@RequestBody State state) {
		return ResponseEntity.status(HttpStatus.CREATED).body(stateService.save(state));
	}

	@PutMapping("/{id}")
	public ResponseEntity<State> update(@PathVariable Long id, @RequestBody State state) {
		Optional<State> stateNow = stateRepository.findById(id);
		if (stateNow.isPresent()) {
		  BeanUtils.copyProperties(state, stateNow.get(), "id");
		  stateService.save(stateNow.get());
		  return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<State> delete(@PathVariable Long id) {
    	stateService.delete(id);
    	return ResponseEntity.noContent().build();
	}
}
