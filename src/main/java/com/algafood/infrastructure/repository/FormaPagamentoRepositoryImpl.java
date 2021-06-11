package com.algafood.infrastructure.repository;

import com.algafood.domain.entity.FormaPagamento;
import com.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

  @PersistenceContext private EntityManager entityManager;

  @Override
  public List<FormaPagamento> findAll() {
    TypedQuery<FormaPagamento> query =
        entityManager.createQuery("from FormaPagamento", FormaPagamento.class);
    return query.getResultList();
  }

  @Override
  public FormaPagamento getById(Long id) {
    return entityManager.find(FormaPagamento.class, id);
  }

  @Override
  @Transactional
  public FormaPagamento save(FormaPagamento formaPagamento) {
    return entityManager.merge(formaPagamento);
  }

  @Override
  @Transactional
  public void delete(FormaPagamento formaPagamento) {
    formaPagamento = getById(formaPagamento.getId());
    entityManager.remove(formaPagamento);
  }
}
