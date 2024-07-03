package org.samrtattend.Repository;

import org.samrtattend.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepo extends JpaRepository<Teacher, Integer> {
    List<Teacher> findByOrg(String orgName);
    Teacher findByEmail(String email);
}
