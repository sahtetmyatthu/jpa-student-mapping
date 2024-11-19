package com.example.jpastudentmapping.dao;

import com.example.jpastudentmapping.entity.Student;

import java.util.Optional;

public interface CustomStudentDao {
    Optional<Student>
    getStudentByName(String name);
}
