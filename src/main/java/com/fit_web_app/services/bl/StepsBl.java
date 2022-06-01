package com.fit_web_app.services.bl;

import com.fit_web_app.exceptions.DevException;
import com.fit_web_app.models.entity.StepsEntity;

public interface StepsBl {
    StepsEntity addStep(Integer courseId, String name, String description, String image, Integer stepNumber) throws DevException;

    StepsEntity getStep(Integer stepId);

    void deleteStep(Integer courseId) throws DevException;

    StepsEntity editStep(String name, String description, String image, Integer stepId) throws DevException;
}
