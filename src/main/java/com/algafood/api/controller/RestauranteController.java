package com.algafood.api.controller;

import com.algafood.domain.entity.Restaurante;
import com.algafood.domain.repository.RestauranteRepository;
import com.algafood.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
		Optional<Restaurante> restaurante = restauranteRepository.findById(id);
		return restaurante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

    @PostMapping
	public ResponseEntity<Restaurante> save(@RequestBody Restaurante restaurante) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(restauranteService.save(restaurante));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Restaurante> update(@PathVariable Long id, @RequestBody Restaurante restaurante) {
		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(id);
		if (restauranteAtual.isPresent()) {
		  BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id", "formaPagamentos", "endereco", "dataCadastro", "produtos");
			restauranteService.save(restauranteAtual.get());

		  return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Restaurante> delete(@PathVariable Long id) {
		restauranteService.delete(id);

    	return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Restaurante> updatePart(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(id);
		if (restauranteAtual.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		merge(campos, restauranteAtual.get());
		return update(id, restauranteAtual.get());
	}

	private void merge (Map<String, Object> campos, Restaurante restauranteAtual) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restaurante = objectMapper.convertValue(campos, Restaurante.class);

    	campos.forEach((nome, valor) ->{
			Field field = ReflectionUtils.findField(Restaurante.class, nome);
			field.setAccessible(true);
			Object novoValor = ReflectionUtils.getField(field, restaurante);
			ReflectionUtils.setField(field, restauranteAtual, novoValor);
		});
	}
}
