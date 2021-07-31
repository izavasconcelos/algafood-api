package com.algafood.domain.city.api.v1;

import com.algafood.domain.city.entity.City;
import com.algafood.domain.city.repository.CityRepository;
import com.algafood.domain.city.service.impl.CityServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cidades")
public class CityController {

    private final CityRepository cityRepository;

    private final CityServiceImpl cityService;

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
