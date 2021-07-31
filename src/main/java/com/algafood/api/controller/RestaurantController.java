package com.algafood.api.controller;

import com.algafood.domain.entity.Restaurant;
import com.algafood.domain.repository.RestaurantRepository;
import com.algafood.domain.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurantes")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> findAll() {
        return ResponseEntity.ok(restaurantRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
		Optional<Restaurant> restaurante = restaurantRepository.findById(id);
		return restaurante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

    @PostMapping
	public ResponseEntity<Restaurant> save(@RequestBody Restaurant restaurant) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.save(restaurant));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Restaurant> update(@PathVariable Long id, @RequestBody Restaurant newRestaurant) {
		Optional<Restaurant> restaurant = restaurantRepository.findById(id);
		if (restaurant.isPresent()) {
		  BeanUtils.copyProperties(newRestaurant, restaurant.get(), "id", "paymentType", "address", "createdAt", "products");
			restaurantService.save(restaurant.get());

		  return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Restaurant> delete(@PathVariable Long id) {
		restaurantService.delete(id);

    	return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Restaurant> updatePart(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
		Optional<Restaurant> restaurant = restaurantRepository.findById(id);
		if (restaurant.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		merge(fields, restaurant.get());
		return update(id, restaurant.get());
	}

	private void merge (Map<String, Object> restaurantFields, Restaurant restaurant) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurant newRestaurant = objectMapper.convertValue(restaurantFields, Restaurant.class);

    	restaurantFields.forEach((nome, valor) ->{
			Field field = ReflectionUtils.findField(Restaurant.class, nome);
			field.setAccessible(true);
			Object novoValor = ReflectionUtils.getField(field, newRestaurant);
			ReflectionUtils.setField(field, restaurant, novoValor);
		});
	}
}
