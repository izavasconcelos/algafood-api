package com.algafood.domain.repository;

import com.algafood.domain.entity.Cidade;

import java.util.List;

public interface CidadeRepository {

  List<Cidade> findAll();
  Cidade getById(Long id);
  Cidade save(Cidade cidade);
  void delete(Cidade cidade);
}
