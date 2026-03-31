package dev.horbatiuk.pcds.lab3.controller;

import dev.horbatiuk.pcds.lab3.model.ArchiveRecord;
import dev.horbatiuk.pcds.lab3.service.ArchiveService;
import dev.horbatiuk.pcds.lab3.service.CourseService;
import dev.horbatiuk.pcds.lab3.service.StudentService;
import dev.horbatiuk.pcds.lab3.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/archive")
public class ArchiveController {
    private final ArchiveService archiveService;
    private final StudentService studentService;
    private final CourseService courseService;
    private final TeacherService teacherService;

    public ArchiveController(ArchiveService archiveService,
                             StudentService studentService,
                             CourseService courseService,
                             TeacherService teacherService) {
        this.archiveService = archiveService;
        this.studentService = studentService;
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<ArchiveRecord> getAll() {
        return archiveService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArchiveRecord> getById(@PathVariable Long id) {
        ArchiveRecord record = archiveService.getById(id);
        return record != null ? ResponseEntity.ok(record) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ArchiveRecord record) {
        if (!studentService.exists(record.getStudentId())) {
            return ResponseEntity.badRequest().body("Student with given id does not exist");
        }
        if (!courseService.exists(record.getCourseId())) {
            return ResponseEntity.badRequest().body("Course with given id does not exist");
        }
        if (!teacherService.exists(record.getTeacherId())) {
            return ResponseEntity.badRequest().body("Teacher with given id does not exist");
        }
        if (record.getGrade() == null || record.getGrade() < 0 || record.getGrade() > 100) {
            return ResponseEntity.badRequest().body("Grade must be in range 0..100");
        }
        return ResponseEntity.ok(archiveService.create(record));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ArchiveRecord record) {
        if (!studentService.exists(record.getStudentId())) {
            return ResponseEntity.badRequest().body("Student with given id does not exist");
        }
        if (!courseService.exists(record.getCourseId())) {
            return ResponseEntity.badRequest().body("Course with given id does not exist");
        }
        if (!teacherService.exists(record.getTeacherId())) {
            return ResponseEntity.badRequest().body("Teacher with given id does not exist");
        }
        if (record.getGrade() == null || record.getGrade() < 0 || record.getGrade() > 100) {
            return ResponseEntity.badRequest().body("Grade must be in range 0..100");
        }
        ArchiveRecord updated = archiveService.update(id, record);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return archiveService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}