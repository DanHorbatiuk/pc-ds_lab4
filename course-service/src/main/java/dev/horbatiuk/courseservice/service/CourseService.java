package dev.horbatiuk.courseservice.service;

import dev.horbatiuk.courseservice.dto.TeacherDto;
import dev.horbatiuk.courseservice.model.Course;
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
public class CourseService {

    private final Map<Long, Course> courses = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(1);
    private final RestTemplate restTemplate;

    @Value("${teacher.service.url}")
    private String teacherServiceUrl;

    public CourseService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Course> getAll() {
        return new ArrayList<>(courses.values());
    }

    public Course getById(Long id) {
        return courses.get(id);
    }

    public Course create(Course course) {
        checkTeacherExists(course.getTeacherId());

        Long id = counter.getAndIncrement();
        course.setId(id);
        courses.put(id, course);
        return course;
    }

    public Course update(Long id, Course updatedCourse) {
        if (!courses.containsKey(id)) {
            return null;
        }

        checkTeacherExists(updatedCourse.getTeacherId());

        updatedCourse.setId(id);
        courses.put(id, updatedCourse);
        return updatedCourse;
    }

    public boolean delete(Long id) {
        return courses.remove(id) != null;
    }

    private void checkTeacherExists(Long teacherId) {
        try {
            TeacherDto teacher = restTemplate.getForObject(
                    teacherServiceUrl + "/teachers/" + teacherId,
                    TeacherDto.class
            );

            if (teacher == null) {
                throw new RuntimeException("Teacher with id " + teacherId + " does not exist");
            }
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("Teacher with id " + teacherId + " does not exist");
        }
    }
}