package dev.horbatiuk.pcds.lab3.service;

import dev.horbatiuk.pcds.lab3.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TeacherService {
    private final Map<Long, Teacher> teachers = new LinkedHashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Teacher> getAll() {
        return new ArrayList<>(teachers.values());
    }

    public Teacher getById(Long id) {
        return teachers.get(id);
    }

    public Teacher create(Teacher teacher) {
        long id = counter.getAndIncrement();
        teacher.setId(id);
        teachers.put(id, teacher);
        return teacher;
    }

    public Teacher update(Long id, Teacher teacher) {
        if (!teachers.containsKey(id)) {
            return null;
        }
        teacher.setId(id);
        teachers.put(id, teacher);
        return teacher;
    }

    public boolean delete(Long id) {
        return teachers.remove(id) != null;
    }

    public boolean exists(Long id) {
        return teachers.containsKey(id);
    }
}