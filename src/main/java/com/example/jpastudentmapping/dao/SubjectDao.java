package com.example.jpastudentmapping.dao;

import com.example.jpastudentmapping.entity.Student;
import com.example.jpastudentmapping.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SubjectDao extends JpaRepository<Subject,Integer> {
//    @Query(value = """
//select * from Student as s join s.studentSubjects as stsub order by stsub.marks desc limit 1
//""",nativeQuery = true)
//    Optional<Student> findStudentHighestMark();
    @Query("""
select s from Student s join s.studentSubjects stusub join stusub.subject sub
where sub.subjectName =:name and stusub.marks =
(select max(sb.marks) from StudentSubject sb join sb.subject su where su.subjectName = :name)
""")
    Optional<Student> findStudentHighestMark(@Param("name") String name);
}
