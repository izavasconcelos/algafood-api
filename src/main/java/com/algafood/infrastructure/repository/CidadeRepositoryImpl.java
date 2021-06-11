package com.algafood.infrastructure.repository;

import com.algafood.domain.entity.Cidade;
import com.algafood.domain.repository.CidadeRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

  @PersistenceContext private EntityManager entityManager;

  @Override
  public List<Cidade> findAll() {
    TypedQuery<Cidade> query =
        entityManager.createQuery("from Cidade", Cidade.class);
    return query.getResultList();
  }

  @Override
  public Cidade getById(Long id) {
    return entityManager.find(Cidade.class, id);
  }

  @Override
  @Transactional
  public Cidade save(Cidade cidade) {
    return entityManager.merge(cidade);
  }

  @Override
  @Transactional
  public void delete(Cidade cidade) {
    cidade = getById(cidade.getId());
    entityManager.remove(cidade);
  }
}
