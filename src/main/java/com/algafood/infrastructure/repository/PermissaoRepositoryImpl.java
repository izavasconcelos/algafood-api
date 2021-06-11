package com.algafood.infrastructure.repository;

import com.algafood.domain.entity.Permissao;
import com.algafood.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

  @PersistenceContext private EntityManager entityManager;

  @Override
  public List<Permissao> findAll() {
    TypedQuery<Permissao> query =
        entityManager.createQuery("from Permissao", Permissao.class);
    return query.getResultList();
  }

  @Override
  public Permissao getById(Long id) {
    return entityManager.find(Permissao.class, id);
  }

  @Override
  @Transactional
  public Permissao save(Permissao permissao) {
    return entityManager.merge(permissao);
  }

  @Override
  @Transactional
  public void delete(Permissao permissao) {
    permissao = getById(permissao.getId());
    entityManager.remove(permissao);
  }
}
