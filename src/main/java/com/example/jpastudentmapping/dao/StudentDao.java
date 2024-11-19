package com.example.jpastudentmapping.dao;

import com.example.jpastudentmapping.entity.Student;
import com.example.jpastudentmapping.entity.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentDao extends
        JpaRepository<Student,Integer>,CustomStudentDao {
    @Query("""
select new com.example.jpastudentmapping.entity.StudentInfo(p.provinceName,s.name,s.email,studsub.marks,sub.subjectName,sub.fees)
from Province p join p.students s join s.studentSubjects 
studsub join studsub.subject sub where sub.subjectName = ?1
""")
    public List<StudentInfo> findStudentInfo(String subjectName);

    Optional<Student> findStudentByName(String name);

    Optional<Student> findByName(String name);
    @Query("""
select max(sb.marks) from StudentSubject sb
""")
    Optional<Integer> findStudentHighestMark();

    @Query("""
select s from Student s join s.studentSubjects sb join sb.subject sub
where sub.subjectName = ?1
""")
    List<Student> getStudentByHighestMarkBySubject(String subjectName);

    @Query("""
select s from Student s where s.name = :name
""")
    Optional<Student> getStudentFromName(@Param("name") String name);

    @Query(value = "select * from student where name = :name",
            nativeQuery = true)
    Optional<Student> getStudentFromNameV2(@Param("name")String name);
}
