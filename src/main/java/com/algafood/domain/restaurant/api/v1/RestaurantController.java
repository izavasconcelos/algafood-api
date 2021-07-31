package com.algafood.domain.restaurant.api.v1;

import com.algafood.domain.restaurant.entity.Restaurant;
import com.algafood.domain.restaurant.service.impl.RestaurantServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurantes")
public class RestaurantController {

    private final RestaurantServiceImpl restaurantService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> findAll() {

        return ResponseEntity.ok(restaurantService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long id) {

		return ResponseEntity.ok(restaurantService.findById(id));
	}

    @PostMapping
	public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.createRestaurant(restaurant));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Restaurant> update(@PathVariable Long id, @RequestBody Restaurant newRestaurant) {
    	restaurantService.update(id, newRestaurant);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Restaurant> delete(@PathVariable Long id) {
		restaurantService.delete(id);

    	return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Restaurant> updatePart(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
    	restaurantService.updatePart(id, fields);

		return ResponseEntity.noContent().build();
	}
}
