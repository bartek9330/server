package com.fit_web_app.services.dao;

import com.fit_web_app.exceptions.DevException;
import com.fit_web_app.models.dto.CourseDTO;
import com.fit_web_app.models.entity.AssignmentsEntity;
import com.fit_web_app.models.entity.CoursesEntity;

import java.util.List;

public interface CoursesDao {
    List<CourseDTO> getAllCourses();

    CoursesEntity getCourse(Integer courseId);

    boolean getCoursesByName(String name);

    CoursesEntity saveCourse(CoursesEntity coursesEntity);

    void deleteCourse(Integer courseId) throws DevException;

    List<CourseDTO> getAvailableCoursesForUser(Integer userId);

    void assignUserToCourse(AssignmentsEntity assignmentsEntity);

    void unassignUserToCourse(Integer userId, Integer courseId);

    List<CourseDTO> getMyCourses(Integer userId);
}
