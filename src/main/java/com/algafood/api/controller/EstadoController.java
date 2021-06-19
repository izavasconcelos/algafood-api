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
import java.util.Optional;

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
		Optional<Estado> estado = estadoRepository.findById(id);
        return estado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

	@PostMapping
	public ResponseEntity<Estado> save(@RequestBody Estado estado) {
		return ResponseEntity.status(HttpStatus.CREATED).body(estadoService.save(estado));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Estado> update(@PathVariable Long id, @RequestBody Estado estado) {
		Optional<Estado> estadoAtual = estadoRepository.findById(id);
		if (estadoAtual.isPresent()) {
		  BeanUtils.copyProperties(estado, estadoAtual.get(), "id");
		  estadoService.save(estadoAtual.get());
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
