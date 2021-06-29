package com.algafood.api.controller;

import com.algafood.domain.entity.Kitchen;
import com.algafood.domain.repository.KitchenRepository;
import com.algafood.domain.service.KitchenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private KitchenService kitchenService;

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
	public ResponseEntity<Kitchen> update(@PathVariable Long id, @RequestBody Kitchen kitchen) {
    	Optional<Kitchen> cozinhaAtual = kitchenRepository.findById(id);
		if (cozinhaAtual.isPresent()) {
		  BeanUtils.copyProperties(kitchen, cozinhaAtual.get(), "id");
		  kitchenService.save(cozinhaAtual.get());

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
