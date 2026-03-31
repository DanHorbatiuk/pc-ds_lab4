package dev.horbatiuk.teacherservice.service;

import dev.horbatiuk.teacherservice.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TeacherService {

    private final Map<Long, Teacher> teachers = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Teacher> getAll() {
        return new ArrayList<>(teachers.values());
    }

    public Teacher getById(Long id) {
        return teachers.get(id);
    }

    public Teacher create(Teacher teacher) {
        Long id = counter.getAndIncrement();
        teacher.setId(id);
        teachers.put(id, teacher);
        return teacher;
    }

    public Teacher update(Long id, Teacher updatedTeacher) {
        if (!teachers.containsKey(id)) {
            return null;
        }
        updatedTeacher.setId(id);
        teachers.put(id, updatedTeacher);
        return updatedTeacher;
    }

    public boolean delete(Long id) {
        return teachers.remove(id) != null;
    }

    public boolean exists(Long id) {
        return teachers.containsKey(id);
    }
}
