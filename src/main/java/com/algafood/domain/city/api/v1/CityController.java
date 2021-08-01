package com.algafood.domain.city.api.v1;

import com.algafood.domain.city.entity.City;
import com.algafood.domain.city.service.impl.CityServiceImpl;
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
@RequestMapping("/cidades")
public class CityController {

	private final CityServiceImpl cityService;

	@GetMapping
	public ResponseEntity<List<City>> findAll() {
		return ResponseEntity.ok(cityService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<City> findById(@PathVariable Long id) {

		return ResponseEntity.ok(cityService.findById(id));
	}

	@PostMapping
	public ResponseEntity<City> create(@RequestBody City city) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cityService.createCity(city));
	}

	@PutMapping("/{id}")
	public ResponseEntity<City> update(@PathVariable Long id, @RequestBody City newCity) {
		cityService.update(id, newCity);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<City> delete(@PathVariable Long id) {
		cityService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
