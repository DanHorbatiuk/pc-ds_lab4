package dev.horbatiuk.teacherservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private Long id;
    private String fullName;
    private String department;
    private String email;
}