package com.algafood.domain.repository;

import com.algafood.domain.entity.Estado;

import java.util.List;

public interface EstadoRepository {

  List<Estado> findAll();
  Estado getById(Long id);
  Estado save(Estado estado);
  void delete(Estado estado);
}
