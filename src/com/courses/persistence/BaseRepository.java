package com.courses.persistence;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import com.courses.model.BaseEntity;
import com.courses.persistence.helper.PredicateBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;


@Repository
public class BaseRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * SELECT t.* FROM @Table({type}) as t WHERE t.id={id}
     *
     * @param type
     * @param id
     * @param <T>
     * @return
     */


    public <T extends BaseEntity> T getById(Class<T> type, Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        final Root<T> root = cq.from(type);

        Predicate byId = cb.equal(root.get("id"), id);

        cq.where(byId);

        TypedQuery<T> query = em.createQuery(cq);

        return query.getSingleResult();
    }

    public <T> List<T> find(Class<T> type, PredicateBuilder<T> predicateBuilder) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        final Root<T> root = cq.from(type);

//    Predicate securityPredicate = getSecurityPredicate(type);

        if (predicateBuilder != null)
            cq.where(predicateBuilder.toPredicate(cb, cq, root));
//    else
//      cq.where(securityPredicate);
        TypedQuery<T> query = em.createQuery(cq);

        return query.getResultList();
    }

    private <T> Predicate getSecurityPredicate(Class<T> type) {
        return null;
    }

    @Transactional
    public <T extends BaseEntity> T saveOrUpdate(T entity) {
    	System.out.println(em.isOpen());
        if (entity.id != null && !em.contains(entity)) {
            entity = em.merge(entity);
        } else {
            em.persist(entity);
        }
        em.flush();
        return entity;
    }

    @Transactional
    public <T> int delete(Class<T> type, Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<T> cd = cb.createCriteriaDelete(type);
        final Root<T> root = cd.from(type);


        CriteriaQuery<T> cq = cb.createQuery(type);
        Predicate byId = cb.equal(root.get("id"), id);
        cd.where(byId);
        int changes = em.createQuery(cd).executeUpdate();
        em.flush();
        return changes;
    }
}