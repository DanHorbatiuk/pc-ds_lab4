package dev.horbatiuk.enrollmentservice.controller;

import dev.horbatiuk.enrollmentservice.model.Enrollment;
import dev.horbatiuk.enrollmentservice.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public List<Enrollment> getAll() {
        return enrollmentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getById(@PathVariable Long id) {
        Enrollment enrollment = enrollmentService.getById(id);
        return enrollment != null
                ? ResponseEntity.ok(enrollment)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Enrollment enrollment) {
        try {
            return ResponseEntity.ok(enrollmentService.create(enrollment));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        try {
            Enrollment updated = enrollmentService.update(id, enrollment);
            return updated != null
                    ? ResponseEntity.ok(updated)
                    : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return enrollmentService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}