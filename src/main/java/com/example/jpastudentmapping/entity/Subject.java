package com.example.jpastudentmapping.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Subject extends IdClass{
    private String subjectName;
    private String subjectCode;
    private double fees;
    private int durationMonth;
    @OneToMany(mappedBy = "subject",
    cascade = CascadeType.PERSIST)
    private List<StudentSubject> studentSubjects=
            new ArrayList<>();

    public void addStudentSubject(StudentSubject studentSubject){
        studentSubject.setSubject(this);
        studentSubjects.add(studentSubject);
    }

    public Subject(String subjectName, String subjectCode, double fees, int durationMonth) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.fees = fees;
        this.durationMonth = durationMonth;
    }
}
