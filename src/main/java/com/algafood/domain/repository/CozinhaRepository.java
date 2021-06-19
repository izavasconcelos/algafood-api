package com.algafood.domain.repository;

import com.algafood.domain.entity.Cozinha;

import java.util.List;

public interface CozinhaRepository {

  List<Cozinha> findAll();

  List<Cozinha> findByName(String name);

  Cozinha getById(Long id);

  Cozinha save(Cozinha cozinha);

  void delete(Cozinha cozinha);
}
