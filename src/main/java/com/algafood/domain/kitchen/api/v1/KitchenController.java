package com.algafood.domain.kitchen.api.v1;

import com.algafood.domain.kitchen.entity.Kitchen;
import com.algafood.domain.kitchen.service.KitchenService;
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
@RequestMapping("/cozinhas")
public class KitchenController {

	private final KitchenService kitchenService;

	@GetMapping
	public ResponseEntity<List<Kitchen>> findAll() {
		return ResponseEntity.ok(kitchenService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Kitchen> findById(@PathVariable Long id) {

		return ResponseEntity.ok(kitchenService.findById(id));
	}

	@PostMapping
	public ResponseEntity<Kitchen> create(@RequestBody Kitchen kitchen) {
		return ResponseEntity.status(HttpStatus.CREATED).body(kitchenService.createKitchen(kitchen));
	}

	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable Long id, @RequestBody Kitchen newKitchen) {

		kitchenService.update(id, newKitchen);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Kitchen> delete(@PathVariable Long id) {
		kitchenService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
