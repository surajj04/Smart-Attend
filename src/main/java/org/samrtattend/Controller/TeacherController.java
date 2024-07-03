package org.samrtattend.Controller;

import org.samrtattend.Model.Attendance;
import org.samrtattend.Model.AttendanceRequest;
import org.samrtattend.Model.Student;
import org.samrtattend.Model.Teacher;
import org.samrtattend.Repository.AttendanceRepo;
import org.samrtattend.Repository.StudentRepo;
import org.samrtattend.Repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TeacherController {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    AttendanceRepo attendanceRepo;

    @RequestMapping("/students")
    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    @GetMapping("/studentsByOrg/{_class}")
    public List<Student> getAllStudentByOrg(@PathVariable String _class) {
        return studentRepo.findBy_class(_class);
    }

    @RequestMapping("/updateTeacher")
    public boolean updateProfile(@RequestBody Teacher teacher) {
        return teacherRepo.save(teacher) != null;
    }

    @PostMapping("/uploadAttendance")
    public boolean uploadAttend(@RequestBody AttendanceRequest attend) {
        Attendance a = new Attendance();

        a.setAttend(Arrays.toString(attend.getAttend()));
        a.setDate(new Date());
        a.set_class(attend.get_class());
        a.setOrg(attend.getOrg());
        attendanceRepo.save(a);

        return true;
    }

    @GetMapping("/getAttendance/{_class}/{org}")
    public List<Attendance> getAttendance(@PathVariable String _class, @PathVariable String org) {
        List<Attendance> attendances = attendanceRepo.findByClassAndOrg(_class, org);
        return attendances;
    }
}
