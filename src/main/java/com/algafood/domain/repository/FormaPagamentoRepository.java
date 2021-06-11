package com.algafood.domain.repository;

import com.algafood.domain.entity.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {

  List<FormaPagamento> findAll();
  FormaPagamento getById(Long id);
  FormaPagamento save(FormaPagamento formaPagamento);
  void delete(FormaPagamento formaPagamento);
}
