package com.fit_web_app;

import com.fit_web_app.configuration.AbstractDao;
import com.fit_web_app.exceptions.DevException;
import com.fit_web_app.models.entity.CoursesEntity;
import com.fit_web_app.services.bl.CoursesBl;
import com.fit_web_app.services.bl.CoursesBlImpl;
import com.fit_web_app.services.bl.StepsBl;
import com.fit_web_app.services.bl.StepsBlImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CoursesTest {
    private static CoursesBl coursesBl;

    private static StepsBl stepsBl;

    @BeforeAll
    public static void init() {
        AbstractDao.init();
        coursesBl = new CoursesBlImpl();
        stepsBl = new StepsBlImpl();
    }

    @Test
    public void getAllCoursesTest() {
        Assertions.assertEquals(1, coursesBl.getAllCourses().size());
    }

    @Test
    public void findFirstCourseTest() {
        Assertions.assertNotNull(coursesBl.getCourse(1));
    }

    @Test
    public void insertAndDeleteCourseTest() throws DevException {
        CoursesEntity coursesEntity = coursesBl.addCourse("test", "test", "ssss");
        Assertions.assertNotNull(coursesBl.getCourse(coursesEntity.getId()));
        coursesBl.deleteCourse(coursesEntity.getId());
        Assertions.assertNull(coursesBl.getCourse(coursesEntity.getId()));
    }

    @Test
    public void changeCourseDataTest() throws DevException {
        CoursesEntity coursesEntity = coursesBl.addCourse("test", "test", "ssss");
        Assertions.assertNotNull(coursesBl.getCourse(coursesEntity.getId()));
        coursesBl.editCourse(coursesEntity.getId(), "NOWA_NAZWA", "NOWY_OPIS", "ssss");
        CoursesEntity newCoursesEntity = coursesBl.getCourse(coursesEntity.getId());
        Assertions.assertEquals(newCoursesEntity.getName(), "NOWA_NAZWA");
        Assertions.assertEquals(newCoursesEntity.getDescription(), "NOWY_OPIS");
        coursesBl.deleteCourse(coursesEntity.getId());
        Assertions.assertNull(coursesBl.getCourse(coursesEntity.getId()));
    }


    @Test
    public void createAndDeleteCourseWithSteps() throws DevException {
        CoursesEntity coursesEntity = coursesBl.addCourse("test100", "test", "ssss");
        stepsBl.addStep(coursesEntity.getId(), "teswt", "test", "sss", 1);
        coursesBl.deleteCourse(coursesEntity.getId());
        Assertions.assertNull(coursesBl.getCourse(coursesEntity.getId()));
    }
}