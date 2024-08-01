package org.samrtattend.Controller;

import org.samrtattend.Model.Organization;
import org.samrtattend.Model.Student;
import org.samrtattend.Model.Teacher;
import org.samrtattend.Model.User;
import org.samrtattend.Repository.OrganizationRepo;
import org.samrtattend.Repository.StudentRepo;
import org.samrtattend.Repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://localhost:8443")
@RestController
public class OrganizationController {

    @Autowired
    OrganizationRepo organizationRepo;
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    TeacherRepo teacherRepo;

    @GetMapping("/organizationDetail/{email}")
    public Organization getOrg(@PathVariable String email){
        return organizationRepo.findByEmail(email);
    }

    @GetMapping("/orgStudents/{orgName}")
    public List<Student> getAllStudents(@PathVariable String orgName) {
        return studentRepo.findByOrg(orgName);
    }

    @GetMapping("/orgTeachers/{orgName}")
    public List<Teacher> getAllTeachers(@PathVariable String orgName) {
        List<Teacher> teachers = teacherRepo.findByOrg(orgName);
        return teachers;
    }

    @PostMapping("/updateStudent")
    public boolean updateStudent(@RequestBody Student student) {
        Student s = studentRepo.findByEmail(student.getEmail());
        s.setName(student.getName());
        s.setEmail(student.getEmail());
        s.setDate(student.getDate());
        s.setOrg(student.getOrg());
        s.set_class(student.get_class());

        return studentRepo.save(s) != null;
    }

    @PostMapping("/deleteStudent")
    public boolean deleteStudent(@RequestBody Student student) {
        studentRepo.deleteById(student.getId());
        return true;
    }

    @PostMapping("/updateTeacher")
    public boolean updateTeacher(@RequestBody Teacher teacher) {
        Teacher t = teacherRepo.findByEmail(teacher.getEmail());
        t.setName(teacher.getName());
        t.setEmail(teacher.getEmail());
        t.setDate(teacher.getDate());
        t.setOrg(teacher.getOrg());
        t.set_class(teacher.get_class());

        return teacherRepo.save(t) != null;
    }

    @PostMapping("/deleteTeacher")
    public boolean deleteTeacher(@RequestBody Teacher teacher) {
        teacherRepo.deleteById(teacher.getId());
        return true;
    }

    @PostMapping("/updateOrg")
    public boolean updateOrganization(@RequestBody Organization organization) {
        Organization org = organizationRepo.findByEmail(organization.getEmail());

        org.setName(organization.getName());
        org.setCode(organization.getCode());
        org.setEmail(org.getEmail());

        return organizationRepo.save(org) != null;
    }

}
