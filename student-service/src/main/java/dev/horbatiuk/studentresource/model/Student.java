package dev.horbatiuk.studentresource.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Long id;
    private String fullName;
    private String groupName;
    private String email;
}