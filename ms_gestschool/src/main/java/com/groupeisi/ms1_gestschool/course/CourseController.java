package com.groupeisi.ms1_gestschool.course;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CourseController {

    private final CourseService courseService;
    private  static  final Logger LOG  = LogManager.getLogger(Controller.class);
    @PostMapping
    public ResponseEntity<CourseDtoResponse> createCourse(@RequestBody @Valid  CourseDtoRequest request) {
        return ResponseEntity.ok(courseService.createCourse(request));
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCourses() {
        List<CourseDtoResponse> courses = courseService.getAllCourses();
        Map<String, Object> response = new HashMap<>();
        response.put("count", courses.size());
        response.put("courses", courses);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCourseById(@PathVariable Long id) {
        Optional<CourseDtoResponse> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Course found");
            response.put("course", course.get());
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Course not found");
            return ResponseEntity.status(404).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateCourse(@PathVariable Long id, @RequestBody @Valid CourseDtoRequest request) {
        Optional<CourseDtoResponse> updatedCourse = courseService.updateCourse(id, request);
        if (updatedCourse.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Course updated successfully");
            response.put("course", updatedCourse.get());
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Course not found");
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteCourse(@PathVariable Long id) {
        boolean deleted = courseService.deleteCourse(id);
        Map<String, Object> response = new HashMap<>();
        if (deleted) {
            response.put("message", "Course deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "Course not found");
            return ResponseEntity.status(404).body(response);
        }
    }
}
