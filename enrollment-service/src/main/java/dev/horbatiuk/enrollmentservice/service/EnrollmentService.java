package dev.horbatiuk.enrollmentservice.service;

import dev.horbatiuk.enrollmentservice.dto.CourseDto;
import dev.horbatiuk.enrollmentservice.dto.StudentDto;
import dev.horbatiuk.enrollmentservice.model.Enrollment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EnrollmentService {

    private final Map<Long, Enrollment> enrollments = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(1);
    private final RestTemplate restTemplate;

    @Value("${student.service.url}")
    private String studentServiceUrl;

    @Value("${course.service.url}")
    private String courseServiceUrl;

    public EnrollmentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Enrollment> getAll() {
        return new ArrayList<>(enrollments.values());
    }

    public Enrollment getById(Long id) {
        return enrollments.get(id);
    }

    public Enrollment create(Enrollment enrollment) {
        checkStudentExists(enrollment.getStudentId());
        checkCourseExists(enrollment.getCourseId());
        checkDuplicate(enrollment.getStudentId(), enrollment.getCourseId(), null);

        Long id = counter.getAndIncrement();
        enrollment.setId(id);
        enrollments.put(id, enrollment);
        return enrollment;
    }

    public Enrollment update(Long id, Enrollment updatedEnrollment) {
        if (!enrollments.containsKey(id)) {
            return null;
        }

        checkStudentExists(updatedEnrollment.getStudentId());
        checkCourseExists(updatedEnrollment.getCourseId());
        checkDuplicate(updatedEnrollment.getStudentId(), updatedEnrollment.getCourseId(), id);

        updatedEnrollment.setId(id);
        enrollments.put(id, updatedEnrollment);
        return updatedEnrollment;
    }

    public boolean delete(Long id) {
        return enrollments.remove(id) != null;
    }

    private void checkStudentExists(Long studentId) {
        try {
            StudentDto student = restTemplate.getForObject(
                    studentServiceUrl + "/students/" + studentId,
                    StudentDto.class
            );
            if (student == null) {
                throw new RuntimeException("Student with id " + studentId + " does not exist");
            }
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("Student with id " + studentId + " does not exist");
        }
    }

    private void checkCourseExists(Long courseId) {
        try {
            CourseDto course = restTemplate.getForObject(
                    courseServiceUrl + "/courses/" + courseId,
                    CourseDto.class
            );
            if (course == null) {
                throw new RuntimeException("Course with id " + courseId + " does not exist");
            }
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("Course with id " + courseId + " does not exist");
        }
    }

    private void checkDuplicate(Long studentId, Long courseId, Long currentId) {
        for (Enrollment enrollment : enrollments.values()) {
            boolean samePair = enrollment.getStudentId().equals(studentId)
                    && enrollment.getCourseId().equals(courseId);

            boolean differentRecord = currentId == null || !enrollment.getId().equals(currentId);

            if (samePair && differentRecord) {
                throw new RuntimeException("This student is already enrolled in this course");
            }
        }
    }
}