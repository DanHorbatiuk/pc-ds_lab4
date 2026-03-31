package dev.horbatiuk.pcds.lab3.service;

import dev.horbatiuk.pcds.lab3.model.Enrollment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EnrollmentService {
    private final Map<Long, Enrollment> enrollments = new LinkedHashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Enrollment> getAll() {
        return new ArrayList<>(enrollments.values());
    }

    public Enrollment getById(Long id) {
        return enrollments.get(id);
    }

    public Enrollment create(Enrollment enrollment) {
        long id = counter.getAndIncrement();
        enrollment.setId(id);
        enrollments.put(id, enrollment);
        return enrollment;
    }

    public Enrollment update(Long id, Enrollment enrollment) {
        if (!enrollments.containsKey(id)) {
            return null;
        }
        enrollment.setId(id);
        enrollments.put(id, enrollment);
        return enrollment;
    }

    public boolean delete(Long id) {
        return enrollments.remove(id) != null;
    }

    public boolean exists(Long id) {
        return enrollments.containsKey(id);
    }

    public boolean isStudentAlreadyEnrolled(Long studentId, Long courseId) {
        return enrollments.values().stream()
                .anyMatch(e -> e.getStudentId().equals(studentId) && e.getCourseId().equals(courseId));
    }
}
