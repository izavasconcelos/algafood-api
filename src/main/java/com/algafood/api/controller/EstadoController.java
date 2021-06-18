package com.algafood.api.controller;

import com.algafood.domain.entity.Estado;
import com.algafood.domain.repository.EstadoRepository;
import com.algafood.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
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

    @Autowired
	private EstadoService estadoService;

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
		return ResponseEntity.status(HttpStatus.CREATED).body(estadoService.save(estado));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Estado> update(@PathVariable Long id, @RequestBody Estado estado) {
		Estado estadoAtual = estadoRepository.getById(id);
		if (estadoAtual != null) {
		  BeanUtils.copyProperties(estado, estadoAtual, "id");
		  estadoService.save(estadoAtual);

		  return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Estado> delete(@PathVariable Long id) {
    	estadoService.delete(id);

    	return ResponseEntity.noContent().build();
	}
}
