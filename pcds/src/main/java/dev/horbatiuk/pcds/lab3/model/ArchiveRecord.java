package dev.horbatiuk.pcds.lab3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchiveRecord {
    private Long id;
    private Long studentId;
    private Long courseId;
    private Long teacherId;
    private Integer grade;
    private String completedAt;
}