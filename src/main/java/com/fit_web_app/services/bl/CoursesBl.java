package com.fit_web_app.services.bl;

import com.fit_web_app.exceptions.DevException;
import com.fit_web_app.models.dto.CourseDTO;
import com.fit_web_app.models.entity.CoursesEntity;

import java.util.List;

public interface CoursesBl {
    List<CourseDTO> getAllCourses();

    CoursesEntity getCourse(Integer courseId);

    CoursesEntity addCourse(String name, String description, String image) throws DevException;

    void deleteCourse(Integer courseId) throws DevException;

    void editCourse(Integer courseId, String name, String description, String image) throws DevException;

    List<CourseDTO> getAvailableCoursesForUser(Integer userId) throws DevException;

    void assignUserToCourse(Integer userId, Integer courseId) throws DevException;

    void unassignUserToCourse(Integer userId, Integer courseId) throws DevException;

    List<CourseDTO> getMyCourses(Integer userId) throws DevException;
}
