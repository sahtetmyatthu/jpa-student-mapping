package com.example.jpastudentmapping.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Student extends IdClass{
    private String name;
    private int age;
    private String email;

    @ManyToOne
    private Province province;
    @OneToMany(mappedBy = "student",
            cascade = CascadeType.PERSIST)
    private List<StudentSubject> studentSubjects=
            new ArrayList<>();

    public void
        addStudentSubject(StudentSubject studentSubject){
        studentSubject.setStudent(this);
        studentSubjects.add(studentSubject);
    }

    public Student(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", province=" + province.getProvinceName() +
                '}';
    }
}
