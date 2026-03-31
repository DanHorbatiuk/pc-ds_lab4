package dev.horbatiuk.studentresource.service;

import dev.horbatiuk.studentresource.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Student> getAll() {
        return new ArrayList<>(students.values());
    }

    public Student getById(Long id) {
        return students.get(id);
    }

    public Student create(Student student) {
        Long id = counter.getAndIncrement();
        student.setId(id);
        students.put(id, student);
        return student;
    }

    public Student update(Long id, Student updatedStudent) {
        if (!students.containsKey(id)) {
            return null;
        }
        updatedStudent.setId(id);
        students.put(id, updatedStudent);
        return updatedStudent;
    }

    public boolean delete(Long id) {
        return students.remove(id) != null;
    }

    public boolean exists(Long id) {
        return students.containsKey(id);
    }
}