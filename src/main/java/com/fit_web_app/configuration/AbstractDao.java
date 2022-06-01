package com.fit_web_app.configuration;

import com.fit_web_app.exceptions.DevException;
import com.fit_web_app.models.entity.AbstractDomain;

import javax.persistence.*;
import java.util.List;

public class AbstractDao {
    @PersistenceContext
    public static EntityManager entityManager;

    public static void init() {
        entityManager = Persistence.createEntityManagerFactory("persistence").createEntityManager();
    }

    protected void delete(AbstractDomain domain) throws DevException {
        if (domain != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(domain);
            entityManager.getTransaction().commit();
        } else {
            throw new DevException("Element does not exist");
        }
    }

    protected <T> T save(T domains) {
        if (domains != null) {
            entityManager.getTransaction().begin();
            T domain = entityManager.merge(domains);
            entityManager.getTransaction().commit();

            return domain;
        }

        return null;
    }

    public void flush() {
        entityManager.flush();
    }

    public void clear() {
        entityManager.clear();
    }

    public <T> T getSingleResult(List<T> resultList) {
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        }

        return null;
    }

    protected <T> T singleResult(Query query) {
        try {
            return (T) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            clear();
        }
    }

    protected <T> List<T> listResult(Query query) {
        try {
            return query.getResultList();
        } finally {
            clear();
        }
    }

    protected Query createNativeQuery(String sql) {
        return entityManager.createNativeQuery(sql);
    }

    protected void update(Query query) {
        if (query != null) {
            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
        }
    }
}