package dev.horbatiuk.enrollmentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    private Long id;
    private Long studentId;
    private Long courseId;
}