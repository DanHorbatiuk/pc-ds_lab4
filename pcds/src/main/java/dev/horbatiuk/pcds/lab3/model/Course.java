package dev.horbatiuk.pcds.lab3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private Long id;
    private String name;
    private String description;
    private Integer credits;
    private Long teacherId;
}