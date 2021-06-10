package com.algafood.domain.repository;

import com.algafood.domain.entity.Restaurante;

import java.util.List;

public interface RestauranteRepository {

  List<Restaurante> findAll();
  Restaurante getById(Long id);
  Restaurante save(Restaurante restaurante);
  void delete(Restaurante restaurante);
}
