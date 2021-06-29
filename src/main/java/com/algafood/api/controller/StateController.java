package com.algafood.api.controller;

import com.algafood.domain.entity.State;
import com.algafood.domain.repository.StateRepository;
import com.algafood.domain.service.StateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
	private StateService stateService;

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
		Optional<State> estadoAtual = stateRepository.findById(id);
		if (estadoAtual.isPresent()) {
		  BeanUtils.copyProperties(state, estadoAtual.get(), "id");
		  stateService.save(estadoAtual.get());
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
