package com.algafood.infrastructure.repository;

import com.algafood.domain.entity.Restaurante;
import com.algafood.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

  @PersistenceContext private EntityManager entityManager;

  @Override
  public List<Restaurante> findAll() {
    TypedQuery<Restaurante> query =
        entityManager.createQuery("from Restaurante", Restaurante.class);
    return query.getResultList();
  }

  @Override
  public Restaurante getById(Long id) {
    return entityManager.find(Restaurante.class, id);
  }

  @Override
  @Transactional
  public Restaurante save(Restaurante restaurante) {
    return entityManager.merge(restaurante);
  }

  @Override
  @Transactional
  public void delete(Restaurante restaurante) {
    restaurante = getById(restaurante.getId());
    entityManager.remove(restaurante);
  }
}
