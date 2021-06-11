package com.algafood.domain.repository;

import com.algafood.domain.entity.Permissao;

import java.util.List;

public interface PermissaoRepository {

  List<Permissao> findAll();
  Permissao getById(Long id);
  Permissao save(Permissao permissao);
  void delete(Permissao permissao);
}
