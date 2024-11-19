package com.example.jpastudentmapping;

import com.example.jpastudentmapping.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class JpaStudentMappingApplication implements CommandLineRunner {
    private final StudentService studentService;

    public static void main(String[] args) {
        SpringApplication.run(JpaStudentMappingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // studentService.createDb();

//        System.out.println("Student By StudentName::");
//        System.out.println(studentService.getStudentByNameCustom("John Doe"));
//        System.out.println("Student By Dynamic Query Example");
//        Student s=new Student();
//        s.setName("John Doe");
//        s.setEmail("john@gmail.com");
//        Example<Student> studentExample=Example.of(s);
//        System.out.println(studentService.studentByExample(studentExample));
//        System.out.println("Student By Highest Marks");
//        studentService.findStudentHighestMarksInSubject("Java")
//                        .forEach(System.out::println);
        System.out.println("Highest Marks");
        System.out.println(studentService.highestMark());
        //System.out.println(studentService.findStudentHighestMarksInSubject("Java"));
        studentService.findStudentInfo("Java")
                .forEach(System.out::println);
        System.out.println();
        System.out.println("Highest Mark Student in Java");
        System.out.println(studentService.studentHighestMark("Java"));

        JPAUtil.checkData("select * from province");
        JPAUtil.checkData("select * from student");
        JPAUtil.checkData("select * from subject");
        JPAUtil.checkData("select * from student_subject");
    }
}
