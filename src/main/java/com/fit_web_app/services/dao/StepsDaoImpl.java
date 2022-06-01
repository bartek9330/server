package com.fit_web_app.services.dao;

import com.fit_web_app.configuration.AbstractDao;
import com.fit_web_app.exceptions.DevException;
import com.fit_web_app.models.entity.StepsEntity;

import javax.persistence.Query;
import java.util.List;

public class StepsDaoImpl extends AbstractDao implements StepsDao {
    @Override
    public StepsEntity addStep(StepsEntity stepsEntity) {
        return save(stepsEntity);
    }

    @Override
    public StepsEntity getStep(Integer stepId) {
        try {
            return entityManager.find(StepsEntity.class, stepId);
        } finally {
            clear();
        }
    }

    @Override
    public void deleteStep(Integer stepId) throws DevException {
        delete(entityManager.find(StepsEntity.class, stepId));
    }

    @Override
    public boolean checkIfStepExistsByName(String name, Integer courseId) {
        Query query = entityManager.createQuery("select s from StepsEntity s where LOWER(name) = LOWER(:stepName) and courseId = :courseId");
        query.setParameter("stepName", name);
        query.setParameter("courseId", courseId);

        return singleResult(query) != null;
    }

    @Override
    public List<StepsEntity> getStepByCourseId(Integer courseId) {
        return getStepByCourseId(courseId, 0);
    }

    @Override
    public List<StepsEntity> getStepByCourseId(Integer courseId, Integer stepNumber) {
        Query query = entityManager.createQuery("select s from StepsEntity s where s.courseId = :courseId and s.stepNumber >= :stepNumber order by s.stepNumber");

        query.setParameter("courseId", courseId);
        query.setParameter("stepNumber", stepNumber);

        return query.getResultList();
    }

    @Override
    public Integer getCourseIdByStep(Integer stepId) {
        Query query = entityManager.createQuery("select s.courseId from StepsEntity s where s.id = :stepId");
        query.setParameter("stepId", stepId);

        return singleResult(query);
    }

    @Override
    public boolean checkIfStepExistsByStepNumber(Integer stepNumber, Integer courseId) {
        Query query = entityManager.createQuery("select s from StepsEntity s where s.stepNumber = :stepNumber and courseId = :courseId");
        query.setParameter("stepNumber", stepNumber);
        query.setParameter("courseId", courseId);

        return singleResult(query) != null;
    }

    @Override
    public void deleteSteps(Integer courseId) {
        Query query = entityManager.createQuery("delete StepsEntity s where s.courseId = :courseId");
        query.setParameter("courseId", courseId);

        update(query);
    }
}