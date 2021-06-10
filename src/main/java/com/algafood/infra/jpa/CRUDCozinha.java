package com.algafood.infra.jpa;

import com.algafood.domain.entity.Cozinha;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CRUDCozinha {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Cozinha> listar() {
        TypedQuery<Cozinha> query = entityManager.createQuery("from Cozinha", Cozinha.class);
        return  query.getResultList();
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return entityManager.merge(cozinha);
    }

    public Cozinha getById(Long id) {
        return entityManager.find(Cozinha.class, id);
    }

    @Transactional
    public void deletar(Cozinha cozinha) {
        cozinha = getById(cozinha.getId());
        entityManager.remove(cozinha);
    }
}
