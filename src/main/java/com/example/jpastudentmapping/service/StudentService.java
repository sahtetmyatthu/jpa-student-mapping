package com.example.jpastudentmapping.service;

import com.example.jpastudentmapping.dao.ProvinceDao;
import com.example.jpastudentmapping.dao.StudentDao;
import com.example.jpastudentmapping.dao.StudentSubjectDao;
import com.example.jpastudentmapping.dao.SubjectDao;
import com.example.jpastudentmapping.entity.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final ProvinceDao provinceDao;
    private final StudentDao studentDao;
    private final StudentSubjectDao studentSubjectDao;
    private final SubjectDao subjectDao;

    public Student  studentHighestMark(String name){
        return subjectDao.findStudentHighestMark(name)
                .get();
    }

    public List<Student> studentByExample(Example<Student> studentExample){
        return studentDao.findAll(studentExample);

    }

    public List<StudentInfo> findStudentInfo(String subjectName){
        return studentDao.findStudentInfo(subjectName);
    }

    public int highestMark(){
        return studentDao.findStudentHighestMark()
                .get();
    }

    public List<Student> findStudentHighestMarksInSubject(String subjectName){
        return studentDao.getStudentByHighestMarkBySubject(subjectName);

    }

    public Student findByName(String name){
        return studentDao.getStudentFromNameV2(name)
                .get();
    }


    public Student getStudentByNameCustom(String name){
        return studentDao.getStudentByName(name)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void createDb() {

        Province province1 = new Province("Sule");
        Province province2 = new Province("Hlaing");

        Student student1 = new Student("John Doe", 22, "john@gmail");
        Student student2 = new Student("John William", 22, "william@gmail");
        Student student3 = new Student("John Updike", 22, "updike@gmail");
        Student student4 = new Student("Thomas Hardy", 22, "hardy@gmail");
        Student student5 = new Student("Charles Dickens", 22, "dickens@gmail");

        Subject subject1 =
                new Subject("Java", "001", 2000, 6);
        Subject subject2 =
                new Subject("Python", "002", 1000, 3);
        Subject subject3 =
                new Subject("React", "002", 2500, 6);

        province1.addStudent(student1);
        province1.addStudent(student2);
        province1.addStudent(student3);

        province2.addStudent(student4);
        province2.addStudent(student5);

        StudentSubject studentSubject1=new StudentSubject(90);
        student1.addStudentSubject(studentSubject1);
        subject1.addStudentSubject(studentSubject1);

        StudentSubject studentSubject2=new StudentSubject(80);
        student1.addStudentSubject(studentSubject2);
        subject2.addStudentSubject(studentSubject2);

        StudentSubject studentSubject3=new StudentSubject(85);
        student2.addStudentSubject(studentSubject3);
        subject2.addStudentSubject(studentSubject3);

        StudentSubject studentSubject4=new StudentSubject(92);
        student3.addStudentSubject(studentSubject4);
        subject3.addStudentSubject(studentSubject4);


        StudentSubject studentSubject5=new StudentSubject(75);
        student4.addStudentSubject(studentSubject5);
        subject1.addStudentSubject(studentSubject5);

        StudentSubject studentSubject6=new StudentSubject(80);
        student5.addStudentSubject(studentSubject6);
        subject1.addStudentSubject(studentSubject6);

        provinceDao.save(province1);
        provinceDao.save(province2);

        studentDao.save(student1);
        studentDao.save(student2);
        studentDao.save(student3);
        studentDao.save(student4);
        studentDao.save(student5);




        subjectDao.save(subject1);
        subjectDao.save(subject2);
        subjectDao.save(subject3);

        studentSubjectDao.save(studentSubject1);
        studentSubjectDao.save(studentSubject2);
        studentSubjectDao.save(studentSubject3);
        studentSubjectDao.save(studentSubject4);
        studentSubjectDao.save(studentSubject5);
        studentSubjectDao.save(studentSubject6);


    }
}
