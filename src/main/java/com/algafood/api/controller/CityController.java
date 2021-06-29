package com.algafood.api.controller;

import com.algafood.domain.entity.City;
import com.algafood.domain.repository.CityRepository;
import com.algafood.domain.service.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<City>> findAll() {
        return ResponseEntity.ok(cityRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> findById(@PathVariable Long id) {
		Optional<City> city = cityRepository.findById(id);
		return city.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

	}

    @PostMapping
	public ResponseEntity<City> save(@RequestBody City city) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(cityService.save(city));
	}

	@PutMapping("/{id}")
	public ResponseEntity<City> update(@PathVariable Long id, @RequestBody City newCity) {
		Optional<City> city = cityRepository.findById(id);
		if (city.isPresent()) {
		  BeanUtils.copyProperties(newCity, city.get(), "id");
			cityService.save(city.get());

		  return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<City> delete(@PathVariable Long id) {
		cityService.delete(id);

    	return ResponseEntity.noContent().build();
	}
}
