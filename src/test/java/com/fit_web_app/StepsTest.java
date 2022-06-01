package com.fit_web_app;

import com.fit_web_app.configuration.AbstractDao;
import com.fit_web_app.exceptions.DevException;
import com.fit_web_app.models.entity.StepsEntity;
import com.fit_web_app.services.bl.StepsBl;
import com.fit_web_app.services.bl.StepsBlImpl;
import com.fit_web_app.services.dao.StepsDao;
import com.fit_web_app.services.dao.StepsDaoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class StepsTest {
    private static StepsBl stepsBl;

    private static StepsDao stepsDao;

    @BeforeAll
    public static void init() {
        AbstractDao.init();
        stepsBl = new StepsBlImpl();
        stepsDao = new StepsDaoImpl();
    }

    @Test
    public void insertAndDeleteStepTest() throws DevException {
        StepsEntity stepsEntity = stepsBl.addStep(18, "test", "test", "sss", 1);
        Assertions.assertNotNull(stepsBl.getStep(stepsEntity.getId()));
        stepsBl.deleteStep(stepsEntity.getId());
        Assertions.assertNull(stepsBl.getStep(stepsEntity.getId()));
    }

    @Test
    public void editStepTest() throws DevException {
        StepsEntity stepsEntity = stepsBl.addStep(18, "teswt", "test", "sss", 9999);
        Assertions.assertNotNull(stepsBl.getStep(stepsEntity.getId()));
        stepsEntity = stepsBl.editStep("noweDane", "noweDaneOpis", "sss", stepsEntity.getId());
        Assertions.assertEquals("noweDane", stepsEntity.getName());
        Assertions.assertEquals("noweDaneOpis", stepsEntity.getDescription());
        Assertions.assertEquals(1, stepsEntity.getStepNumber());
        stepsBl.deleteStep(stepsEntity.getId());
        Assertions.assertNull(stepsBl.getStep(stepsEntity.getId()));
    }

    @Test
    public void checkUpdateIndexTest() throws DevException {
        StepsEntity stepsEntity = stepsBl.addStep(18, "test", "test", "sss", 10);
        StepsEntity stepsEntity2 = stepsBl.addStep(18, "test2", "test", "sss", 12);
        StepsEntity stepsEntity3 = stepsBl.addStep(18, "test3", "test", "sss", 13);
        new StepsBlImpl().sortIndexes(stepsDao.getStepByCourseId(stepsEntity.getCourseId()));
        stepsEntity = stepsBl.getStep(stepsEntity.getId());
        stepsEntity2 = stepsBl.getStep(stepsEntity2.getId());
        stepsEntity3 = stepsBl.getStep(stepsEntity3.getId());
        Assertions.assertEquals(1, stepsEntity.getStepNumber());
        Assertions.assertEquals(2, stepsEntity2.getStepNumber());
        Assertions.assertEquals(3, stepsEntity3.getStepNumber());
        stepsBl.deleteStep(stepsEntity.getId());
        stepsBl.deleteStep(stepsEntity2.getId());
        stepsBl.deleteStep(stepsEntity3.getId());
    }

    @Test
    public void checkUpdateIndexWhenStepExist() throws DevException {
        StepsEntity stepsEntity = stepsBl.addStep(18, "test", "test", "sss", 1);
        StepsEntity stepsEntity2 = stepsBl.addStep(18, "test2", "test", "sss", 2);
        StepsEntity stepsEntity3 = stepsBl.addStep(18, "test3", "test", "sss", 3);
        StepsEntity stepsEntity4 = stepsBl.addStep(18, "test4", "test", "sss", 2);

        stepsEntity = stepsBl.getStep(stepsEntity.getId());
        stepsEntity2 = stepsBl.getStep(stepsEntity2.getId());
        stepsEntity3 = stepsBl.getStep(stepsEntity3.getId());
        stepsEntity4 = stepsBl.getStep(stepsEntity4.getId());

        Assertions.assertEquals(1, stepsEntity.getStepNumber());
        Assertions.assertEquals(3, stepsEntity2.getStepNumber());
        Assertions.assertEquals(4, stepsEntity3.getStepNumber());
        Assertions.assertEquals(2, stepsEntity4.getStepNumber());

        stepsBl.deleteStep(stepsEntity.getId());
        stepsBl.deleteStep(stepsEntity2.getId());
        stepsBl.deleteStep(stepsEntity3.getId());
        stepsBl.deleteStep(stepsEntity4.getId());
    }
}