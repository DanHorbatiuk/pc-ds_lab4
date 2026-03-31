package dev.horbatiuk.pcds.lab3.controller;

import dev.horbatiuk.pcds.lab3.model.Course;
import dev.horbatiuk.pcds.lab3.service.CourseService;
import dev.horbatiuk.pcds.lab3.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Course> getAll() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable Long id) {
        Course course = courseService.getById(id);
        return course != null ? ResponseEntity.ok(course) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Course course) {
        if (!teacherService.exists(course.getTeacherId())) {
            return ResponseEntity.badRequest().body("Teacher with given id does not exist");
        }
        return ResponseEntity.ok(courseService.create(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Course course) {
        if (!teacherService.exists(course.getTeacherId())) {
            return ResponseEntity.badRequest().body("Teacher with given id does not exist");
        }
        Course updated = courseService.update(id, course);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return courseService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
