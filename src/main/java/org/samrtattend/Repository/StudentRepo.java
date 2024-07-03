package org.samrtattend.Repository;

import org.samrtattend.Model.Attendance;
import org.samrtattend.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student,Integer> {
    Student findByEmail(String email);

    List<Student> findByOrg(String org);

    List<Student> findBy_class(String aClass);

    @Query("SELECT a FROM Student a WHERE a._class = :_class AND a.org = :org")
    List<Student> findByClassOrg(@Param("_class") String _class,@Param("org") String org);

}
