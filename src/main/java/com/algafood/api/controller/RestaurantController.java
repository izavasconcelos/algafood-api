package com.algafood.api.controller;

import com.algafood.domain.entity.Restaurant;
import com.algafood.domain.repository.RestaurantRepository;
import com.algafood.domain.service.RestaurantService;
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
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

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
