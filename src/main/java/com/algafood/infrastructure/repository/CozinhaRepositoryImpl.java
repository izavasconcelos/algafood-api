package com.algafood.infrastructure.repository;

import com.algafood.domain.entity.Cozinha;
import com.algafood.domain.repository.CozinhaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Cozinha> findAll() {
    TypedQuery<Cozinha> query = entityManager.createQuery("from Cozinha", Cozinha.class);
    return  query.getResultList();
  }

  @Override
  public Cozinha getById(Long id) {
    return entityManager.find(Cozinha.class, id);
  }

  @Override
  @Transactional
  public Cozinha save(Cozinha cozinha) {
    return entityManager.merge(cozinha);
  }

  @Override
  @Transactional
  public void delete(Cozinha cozinha) {
    cozinha = getById(cozinha.getId());
    entityManager.remove(cozinha);
  }
}
