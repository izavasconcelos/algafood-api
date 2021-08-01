package com.algafood.domain.restaurant.repository;

import com.algafood.domain.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

	boolean existsByNameAndKitchenId(final String name, final Long id);
}
