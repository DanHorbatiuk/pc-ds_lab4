package dev.horbatiuk.pcds.lab3.service;

import dev.horbatiuk.pcds.lab3.model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CourseService {
    private final Map<Long, Course> courses = new LinkedHashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Course> getAll() {
        return new ArrayList<>(courses.values());
    }

    public Course getById(Long id) {
        return courses.get(id);
    }

    public Course create(Course course) {
        long id = counter.getAndIncrement();
        course.setId(id);
        courses.put(id, course);
        return course;
    }

    public Course update(Long id, Course course) {
        if (!courses.containsKey(id)) {
            return null;
        }
        course.setId(id);
        courses.put(id, course);
        return course;
    }

    public boolean delete(Long id) {
        return courses.remove(id) != null;
    }

    public boolean exists(Long id) {
        return courses.containsKey(id);
    }
}