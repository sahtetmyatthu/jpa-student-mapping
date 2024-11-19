package com.example.jpastudentmapping.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
public class Province extends IdClass{
    private String provinceName;

    @OneToMany(mappedBy = "province",
    cascade = CascadeType.PERSIST)
    private List<Student> students=
            new ArrayList<>();

    public void addStudent(Student student){
        student.setProvince(this);
        this.students.add(student);
    }

    public Province(String provinceName) {
        this.provinceName = provinceName;
    }
}
