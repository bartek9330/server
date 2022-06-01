package com.fit_web_app.services.bl;

import com.fit_web_app.exceptions.DevException;
import com.fit_web_app.models.dto.CourseDTO;
import com.fit_web_app.models.entity.AssignmentsEntity;
import com.fit_web_app.models.entity.CoursesEntity;
import com.fit_web_app.models.entity.UsersEntity;
import com.fit_web_app.services.dao.CoursesDao;
import com.fit_web_app.services.dao.CoursesDaoImpl;
import com.fit_web_app.services.dao.StepsDao;
import com.fit_web_app.services.dao.StepsDaoImpl;

import java.util.List;
import java.util.ResourceBundle;

public class CoursesBlImpl implements CoursesBl {
    private final CoursesDao coursesDao;

    private final StepsDao stepsDao;

    private final ResourceBundle messages = ResourceBundle.getBundle("messages");

    public CoursesBlImpl() {
        coursesDao = new CoursesDaoImpl();
        stepsDao = new StepsDaoImpl();
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return coursesDao.getAllCourses();
    }

    @Override
    public CoursesEntity getCourse(Integer courseId) {
        return coursesDao.getCourse(courseId);
    }

    @Override
    public CoursesEntity addCourse(String name, String description, String image) throws DevException {
        if (checkIfCourseExists(name)) {
            throw new DevException(messages.getString("errorCourseExist"));
        }

        if (name == null || name.trim().isEmpty() || description == null || description.trim().isEmpty() || image == null || image.trim().isEmpty()) {
            throw new DevException(messages.getString("errorEmptyDataAddCourse"));
        }

        return coursesDao.saveCourse(new CoursesEntity(name, description, image.getBytes()));
    }

    private boolean checkIfCourseExists(String name) {
        return coursesDao.getCoursesByName(name);
    }

    @Override
    public void deleteCourse(Integer courseId) throws DevException {
        stepsDao.deleteSteps(courseId);
        coursesDao.deleteCourse(courseId);
    }

    @Override
    public void editCourse(Integer courseId, String name, String description, String image) throws DevException {
        CoursesEntity coursesEntity = coursesDao.getCourse(courseId);

        if (name == null || name.trim().isEmpty() || description == null || description.trim().isEmpty() || image == null || image.trim().isEmpty()) {
            throw new DevException(messages.getString("errorEmptyDataAddCourse"));
        }

        coursesEntity.setName(name);
        coursesEntity.setDescription(description);
        coursesEntity.setImage(image.getBytes());

        coursesDao.saveCourse(coursesEntity);
    }

    @Override
    public List<CourseDTO> getAvailableCoursesForUser(Integer userId) throws DevException {
        if (userId == null) {
            throw new DevException(messages.getString("errorEmptyUserId"));
        }

        return coursesDao.getAvailableCoursesForUser(userId);
    }

    @Override
    public void assignUserToCourse(Integer userId, Integer courseId) throws DevException {
        if (userId == null || courseId == null) {
            throw new DevException(messages.getString("errorEmptyUserIdOrCourseId"));
        }

        coursesDao.assignUserToCourse(new AssignmentsEntity(new UsersEntity(userId), new CoursesEntity(courseId)));
    }

    @Override
    public void unassignUserToCourse(Integer userId, Integer courseId) throws DevException {
        if (userId == null || courseId == null) {
            throw new DevException(messages.getString("errorEmptyUserIdOrCourseId"));
        }

        coursesDao.unassignUserToCourse(userId, courseId);
    }

    @Override
    public List<CourseDTO> getMyCourses(Integer userId) throws DevException {
        if (userId == null) {
            throw new DevException(messages.getString("errorEmptyUserId"));
        }

        return coursesDao.getMyCourses(userId);
    }
}
