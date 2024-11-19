package com.example.jpastudentmapping.dao;

import com.example.jpastudentmapping.entity.Student;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomStudentDaoImpl implements CustomStudentDao{
    private final EntityManager em;
    @Override
    public Optional<Student> getStudentByName(String name) {
        Student student=em
                .createQuery("select s from Student s where s.name = ?1"
                ,Student.class)
                .setParameter(1,name)
                .getSingleResult();
        return Optional.ofNullable(student);
    }
}
