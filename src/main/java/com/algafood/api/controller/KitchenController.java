package com.algafood.api.controller;

import com.algafood.domain.entity.Kitchen;
import com.algafood.domain.repository.KitchenRepository;
import com.algafood.domain.service.KitchenService;
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
@RequestMapping("/cozinhas")
public class KitchenController {

    private final KitchenRepository kitchenRepository;

    private final KitchenService kitchenService;

    @GetMapping
    public ResponseEntity<List<Kitchen>> findAll() {
        return ResponseEntity.ok(kitchenRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kitchen> findById(@PathVariable Long id) {

        Optional<Kitchen> cozinha = kitchenRepository.findById(id);
		return cozinha.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

	}

    @PostMapping
	public ResponseEntity<Kitchen> save(@RequestBody Kitchen kitchen) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(kitchenService.save(kitchen));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Kitchen> update(@PathVariable Long id, @RequestBody Kitchen newKitchen) {
    	Optional<Kitchen> kitchen = kitchenRepository.findById(id);
		if (kitchen.isPresent()) {
		  BeanUtils.copyProperties(newKitchen, kitchen.get(), "id");
		  kitchenService.save(kitchen.get());

		  return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Kitchen> delete(@PathVariable Long id) {
    	kitchenService.delete(id);

    	return ResponseEntity.noContent().build();
	}
}
