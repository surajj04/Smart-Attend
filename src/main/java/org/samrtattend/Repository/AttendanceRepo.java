package org.samrtattend.Repository;

import org.samrtattend.Model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttendanceRepo extends JpaRepository<Attendance, Integer> {
    @Query("SELECT a FROM Attendance a WHERE a._class = :_class AND a.org = :org")
    List<Attendance> findByClassAndOrg(@Param("_class") String _class, @Param("org") String org);
}
