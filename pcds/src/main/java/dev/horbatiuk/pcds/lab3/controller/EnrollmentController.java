package dev.horbatiuk.pcds.lab3.controller;

import dev.horbatiuk.pcds.lab3.model.Enrollment;
import dev.horbatiuk.pcds.lab3.service.CourseService;
import dev.horbatiuk.pcds.lab3.service.EnrollmentService;
import dev.horbatiuk.pcds.lab3.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentController(EnrollmentService enrollmentService,
                                StudentService studentService,
                                CourseService courseService) {
        this.enrollmentService = enrollmentService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping
    public List<Enrollment> getAll() {
        return enrollmentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getById(@PathVariable Long id) {
        Enrollment enrollment = enrollmentService.getById(id);
        return enrollment != null ? ResponseEntity.ok(enrollment) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Enrollment enrollment) {
        if (!studentService.exists(enrollment.getStudentId())) {
            return ResponseEntity.badRequest().body("Student with given id does not exist");
        }
        if (!courseService.exists(enrollment.getCourseId())) {
            return ResponseEntity.badRequest().body("Course with given id does not exist");
        }
        if (enrollmentService.isStudentAlreadyEnrolled(enrollment.getStudentId(), enrollment.getCourseId())) {
            return ResponseEntity.badRequest().body("Student is already enrolled in this course");
        }
        return ResponseEntity.ok(enrollmentService.create(enrollment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        if (!studentService.exists(enrollment.getStudentId())) {
            return ResponseEntity.badRequest().body("Student with given id does not exist");
        }
        if (!courseService.exists(enrollment.getCourseId())) {
            return ResponseEntity.badRequest().body("Course with given id does not exist");
        }
        Enrollment updated = enrollmentService.update(id, enrollment);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return enrollmentService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}