package com.groupeisi.ms1_gestschool.course;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    CourseDtoResponse createCourse(CourseDtoRequest request);
    List<CourseDtoResponse> getAllCourses();
    Optional<CourseDtoResponse> getCourseById(Long id);
    Optional<CourseDtoResponse> updateCourse(Long id, CourseDtoRequest request);
    boolean deleteCourse(Long id);
}

