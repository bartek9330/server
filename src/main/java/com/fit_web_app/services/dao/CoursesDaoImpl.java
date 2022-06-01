package com.fit_web_app.services.dao;

import com.fit_web_app.configuration.AbstractDao;
import com.fit_web_app.exceptions.DevException;
import com.fit_web_app.models.dto.CourseDTO;
import com.fit_web_app.models.entity.AssignmentsEntity;
import com.fit_web_app.models.entity.CoursesEntity;

import javax.persistence.Query;
import java.util.List;

public class CoursesDaoImpl extends AbstractDao implements CoursesDao {
    @Override
    public List<CourseDTO> getAllCourses() {
        Query query = entityManager.createQuery("select new com.fit_web_app.models.dto.CourseDTO(c.id, c.name, c.description, c.steps.size, c.image) from CoursesEntity c");

        return listResult(query);
    }

    @Override
    public CoursesEntity getCourse(Integer courseId) {
        try {
            return entityManager.find(CoursesEntity.class, courseId);
        } finally {
            clear();
        }
    }

    @Override
    public CoursesEntity saveCourse(CoursesEntity coursesEntity) {
        return save(coursesEntity);
    }

    @Override
    public boolean getCoursesByName(String name) {
        Query query = entityManager.createQuery("select 1 from CoursesEntity c where lower(c.name) = lower(:name)");
        query.setParameter("name", name);

        return singleResult(query) != null;
    }

    @Override
    public void deleteCourse(Integer courseId) throws DevException {
        delete(entityManager.find(CoursesEntity.class, courseId));
    }

    @Override
    public List<CourseDTO> getAvailableCoursesForUser(Integer userId) {
        Query query = entityManager.createQuery("select c from CoursesEntity c where c.id not in ((select a.coursesEntity.id from AssignmentsEntity a where a.userEntity.id = :userId))");
        query.setParameter("userId", userId);

        return listResult(query);
    }

    @Override
    public void assignUserToCourse(AssignmentsEntity assignmentsEntity) {
        save(assignmentsEntity);
    }

    @Override
    public void unassignUserToCourse(Integer userId, Integer courseId) {
        Query query = entityManager.createQuery("delete AssignmentsEntity a where a.userEntity.id = :userId and a.coursesEntity.id = :courseId");
        query.setParameter("userId", userId);
        query.setParameter("courseId", courseId);

        update(query);
    }

    @Override
    public List<CourseDTO> getMyCourses(Integer userId) {
        Query query = entityManager.createQuery("select a.coursesEntity from AssignmentsEntity a where a.userEntity.id = :userId");
        query.setParameter("userId", userId);

        return listResult(query);
    }
}