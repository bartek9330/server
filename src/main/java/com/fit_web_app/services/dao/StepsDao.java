package com.fit_web_app.services.dao;

import com.fit_web_app.exceptions.DevException;
import com.fit_web_app.models.entity.StepsEntity;

import java.util.List;

public interface StepsDao {
    StepsEntity addStep(StepsEntity stepsEntity);

    StepsEntity getStep(Integer stepId);

    void deleteStep(Integer stepId) throws DevException;

    boolean checkIfStepExistsByName(String name, Integer courseId);

    Integer getCourseIdByStep(Integer stepId);

    boolean checkIfStepExistsByStepNumber(Integer stepNumber, Integer courseId);

    List<StepsEntity> getStepByCourseId(Integer courseId, Integer stepNumber);

    List<StepsEntity> getStepByCourseId(Integer courseId);

    void deleteSteps(Integer courseId);
}
