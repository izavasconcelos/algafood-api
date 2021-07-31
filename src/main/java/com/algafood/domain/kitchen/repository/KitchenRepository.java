package com.algafood.domain.kitchen.repository;

import com.algafood.domain.kitchen.entity.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {

	boolean existsByName(String name);
}
