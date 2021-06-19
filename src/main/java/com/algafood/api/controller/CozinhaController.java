package com.algafood.api.controller;

import com.algafood.domain.entity.Cozinha;
import com.algafood.domain.repository.CozinhaRepository;
import com.algafood.domain.service.CozinhaService;
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
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping
    public ResponseEntity<List<Cozinha>> findAll() {
        return ResponseEntity.ok(cozinhaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> findById(@PathVariable Long id) {

        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
		return cozinha.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

	}

    @PostMapping
	public ResponseEntity<Cozinha> save(@RequestBody Cozinha cozinha) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaService.save(cozinha));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> update(@PathVariable Long id, @RequestBody Cozinha cozinha) {
    	Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id);
		if (cozinhaAtual.isPresent()) {
		  BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
		  cozinhaService.save(cozinhaAtual.get());

		  return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cozinha> delete(@PathVariable Long id) {
    	cozinhaService.delete(id);

    	return ResponseEntity.noContent().build();
	}
}
