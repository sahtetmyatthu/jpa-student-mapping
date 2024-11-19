package com.example.jpastudentmapping.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class StudentInfo {
    private String provinceName;
    private String studentName;
    private String email;
    private int marks;
    private String subjectName;
    private double fees;


}
