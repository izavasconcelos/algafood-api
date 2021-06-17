package com.algafood.api.controller;

import com.algafood.domain.entity.Cozinha;
import com.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public ResponseEntity<List<Cozinha>> findAll() {
        return ResponseEntity.ok(cozinhaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> findById(@PathVariable Long id) {

        Cozinha cozinha = cozinhaRepository.getById(id);
        if (cozinha != null) {
            return ResponseEntity.ok(cozinha);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
	public ResponseEntity<Cozinha> save(@RequestBody Cozinha cozinha) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaRepository.save(cozinha));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> update(@PathVariable Long id, @RequestBody Cozinha cozinha) {
    	Cozinha cozinhaAtual = cozinhaRepository.getById(id);
		if (cozinhaAtual != null) {
		  BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		  cozinhaRepository.save(cozinhaAtual);

		  return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
