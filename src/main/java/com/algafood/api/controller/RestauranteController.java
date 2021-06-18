package com.algafood.api.controller;

import com.algafood.domain.entity.Restaurante;
import com.algafood.domain.repository.RestauranteRepository;
import com.algafood.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<List<Restaurante>> findAll() {
        return ResponseEntity.ok(restauranteRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> findById(@PathVariable Long id) {

		Restaurante restaurante = restauranteRepository.getById(id);
        if (restaurante != null) {
            return ResponseEntity.ok(restaurante);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
	public ResponseEntity<Restaurante> save(@RequestBody Restaurante restaurante) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(restauranteService.save(restaurante));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Restaurante> update(@PathVariable Long id, @RequestBody Restaurante restaurante) {
		Restaurante restauranteAtual = restauranteRepository.getById(id);
		if (restauranteAtual != null) {
		  BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
			restauranteService.save(restauranteAtual);

		  return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Restaurante> delete(@PathVariable Long id) {
		restauranteService.delete(id);

    	return ResponseEntity.noContent().build();
	}
}
