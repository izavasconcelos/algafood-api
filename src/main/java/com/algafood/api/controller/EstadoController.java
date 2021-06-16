package com.algafood.api.controller;

import com.algafood.domain.entity.Estado;
import com.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public ResponseEntity<List<Estado>> findAll() {
        return ResponseEntity.ok(estadoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> findById(@PathVariable Long id) {
        return ResponseEntity.ok(estadoRepository.getById(id));
    }

	@PostMapping
	public ResponseEntity<Estado> save(@RequestBody Estado estado) {
		return ResponseEntity.status(HttpStatus.CREATED).body(estadoRepository.save(estado));
	}
}
