package dev.horbatiuk.pcds.lab3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
    private Long id;
    private Long studentId;
    private Long courseId;
    private String enrollmentDate;
    private String status;
}