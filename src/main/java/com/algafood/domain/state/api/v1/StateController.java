package com.algafood.domain.state.api.v1;

import com.algafood.domain.state.entity.State;
import com.algafood.domain.state.service.impl.StateServiceImpl;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/estados")
public class StateController {

	private final StateServiceImpl stateService;

	@GetMapping
	public ResponseEntity<List<State>> findAll() {
		return ResponseEntity.ok(stateService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<State> findById(@PathVariable Long id) {

		return ResponseEntity.ok(stateService.findById(id));
	}

	@PostMapping
	public ResponseEntity<State> create(@RequestBody State state) {
		return ResponseEntity.status(HttpStatus.CREATED).body(stateService.createState(state));
	}

	@PutMapping("/{id}")
	public ResponseEntity<State> update(@PathVariable Long id, @RequestBody State state) {
		stateService.update(id, state);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<State> delete(@PathVariable Long id) {
		stateService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
