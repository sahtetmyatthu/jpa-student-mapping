package com.example.jpastudentmapping.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StudentSubject extends IdClass{
    private int marks;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Subject subject;

    public StudentSubject(int marks) {
        this.marks = marks;
    }
}
