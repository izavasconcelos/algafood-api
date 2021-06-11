package com.algafood.infrastructure.repository;

import com.algafood.domain.entity.Estado;
import com.algafood.domain.entity.Restaurante;
import com.algafood.domain.repository.EstadoRepository;
import com.algafood.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

  @PersistenceContext private EntityManager entityManager;

  @Override
  public List<Estado> findAll() {
    TypedQuery<Estado> query =
        entityManager.createQuery("from Estado", Estado.class);
    return query.getResultList();
  }

  @Override
  public Estado getById(Long id) {
    return entityManager.find(Estado.class, id);
  }

  @Override
  @Transactional
  public Estado save(Estado estado) {
    return entityManager.merge(estado);
  }

  @Override
  @Transactional
  public void delete(Estado estado) {
    estado = getById(estado.getId());
    entityManager.remove(estado);
  }
}
