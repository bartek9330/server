package com.fit_web_app.services.bl;

import com.fit_web_app.exceptions.DevException;
import com.fit_web_app.models.entity.StepsEntity;
import com.fit_web_app.services.dao.StepsDao;
import com.fit_web_app.services.dao.StepsDaoImpl;

import java.util.List;
import java.util.ResourceBundle;

public class StepsBlImpl implements StepsBl {
    private final StepsDao stepsDao;

    private final ResourceBundle messages = ResourceBundle.getBundle("messages");

    public StepsBlImpl() {
        stepsDao = new StepsDaoImpl();
    }

    @Override
    public StepsEntity addStep(Integer courseId, String name, String description, String image, Integer stepNumber) throws DevException {
        if (validateData(courseId, name, description, image, stepNumber)) {
            throw new DevException(messages.getString("errorEmptyDataAddStep"));
        }

        if (checkIfStepExistsByName(name, courseId)) {
            throw new DevException(messages.getString("errorStepExist"));
        }

        if (checkIfStepExistsByStepNumber(stepNumber, courseId)) {
            updateIndexOneUp(stepsDao.getStepByCourseId(courseId, stepNumber));
        }


        StepsEntity stepsEntity = stepsDao.addStep(new StepsEntity(stepNumber, name, description, image.getBytes(), courseId));

        sortIndexes(stepsDao.getStepByCourseId(stepsEntity.getCourseId()));

        return stepsEntity;
    }

    private void updateIndexOneUp(List<StepsEntity> steps) {
        for (StepsEntity step : steps) {
            step.setStepNumber(step.getStepNumber() + 1);
            stepsDao.addStep(step);
        }
    }

    private boolean checkIfStepExistsByStepNumber(Integer stepNumber, Integer courseId) {
        return stepsDao.checkIfStepExistsByStepNumber(stepNumber, courseId);
    }

    private boolean checkIfStepExistsByName(String name, Integer courseId) {
        return stepsDao.checkIfStepExistsByName(name, courseId);
    }

    @Override
    public StepsEntity getStep(Integer stepId) {
        return stepsDao.getStep(stepId);
    }

    @Override
    public void deleteStep(Integer stepId) throws DevException {
        Integer courseId = stepsDao.getCourseIdByStep(stepId);
        stepsDao.deleteStep(stepId);
        sortIndexes(stepsDao.getStepByCourseId(courseId));
    }

    public void sortIndexes(List<StepsEntity> stepList) {
        int stepNumber = 1;
        for (StepsEntity step : stepList) {
            step.setStepNumber(stepNumber);
            stepsDao.addStep(step);
            stepNumber++;
        }
    }

    @Override
    public StepsEntity editStep(String name, String description, String image, Integer stepId) throws DevException {
        if (validateData(name, description, image)) {
            throw new DevException(messages.getString("errorEmptyDataAddStep"));
        }

        StepsEntity stepsEntity = getStep(stepId);
        stepsEntity.setName(name);
        stepsEntity.setDescription(description);
        stepsEntity.setImage(image.getBytes());

        return stepsDao.addStep(stepsEntity);
    }

    private boolean validateData(String name, String description, String image) {
        return (validateData(0, name, description, image, 1));
    }

    private boolean validateData(Integer courseId, String name, String description, String image, Integer stepNumber) {
        return courseId == null || name == null || name.trim().isEmpty() || description == null || description.trim().isEmpty() || image == null || image.trim().isEmpty() || stepNumber == null || stepNumber < 0;
    }
}