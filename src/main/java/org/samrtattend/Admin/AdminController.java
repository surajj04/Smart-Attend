package org.samrtattend.Admin;

import com.mysql.cj.Session;
import org.samrtattend.Model.*;
import org.samrtattend.Repository.OrganizationRepo;
import org.samrtattend.Repository.OtpRepo;
import org.samrtattend.Repository.StudentRepo;
import org.samrtattend.Repository.TeacherRepo;
import org.samrtattend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AdminController {

    @Autowired
    StudentRepo studentRepo;
    @Autowired
    TeacherRepo teacherRepo;
    @Autowired
    OrganizationRepo organizationRepo;
    @Autowired
    EmailService emailService;
    @Autowired
    OtpRepo otpRepo;


    @PostMapping("/user/{email}/{password}")
    public User login(@PathVariable String email, @PathVariable String password) {
        if (studentRepo.findByEmail(email) != null) {
            Student s = studentRepo.findByEmail(email);
            if (s.getPassword().equals(password)) {
                return new User(s.getId(), s.getName(), s.getEmail(), s.getOrg(), s.get_class(), "student", s.getStatus());
            } else {
                return null;
            }
        } else if (teacherRepo.findByEmail(email) != null) {
            Teacher t = teacherRepo.findByEmail(email);
            if (t.getPassword().equals(password)) {
                return new User(t.getId(), t.getName(), t.getEmail(), t.getOrg(), t.get_class(), "teacher", t.getStatus());
            } else {
                return null;
            }
        } else if (organizationRepo.findByEmail(email) != null) {
            Organization org = organizationRepo.findByEmail(email);
            if (org.getPassword().equals(password)) {
                return new User(org.getId(), org.getName(), org.getEmail(), ((Integer) org.getCode()).toString(), "", "org", org.getStatus());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @GetMapping("/org")
    public List<User> getAllOrg() {

        List<Organization> orgs = organizationRepo.findAll();
        List<User> users = new ArrayList<>();
        for (Organization o : orgs) {
            User user = new User(o.getId(), o.getName(), o.getEmail(), ((Integer) o.getCode()).toString(), "", "org", o.getStatus());
            users.add(user);
        }

        return users;
    }

    @PostMapping("/student")
    public boolean registerStudent(@RequestBody Student s) {
        return studentRepo.save(s) != null;
    }

    @PostMapping("/teacher")
    public boolean registerTeacher(@RequestBody Teacher t) {
        return teacherRepo.save(t) != null;
    }

    @PostMapping("/organization")
    public boolean registerOrg(@RequestBody Organization organization) {
        return organizationRepo.save(organization) != null;
    }

    @PostMapping("/sendotp/{userEmail}")
    public boolean sendOtp(@PathVariable String userEmail) {
        String otp = emailService.genOTP();
        Otp obj = new Otp();
        obj.setOtp(otp);
        otpRepo.save(obj);
        emailService.sendEmail(userEmail, "Your One-Time Password (OTP) for Verification", "Dear " + userEmail + ",\n" + "\n" + "Your One-Time Password (OTP) for verification is: " + otp + ".\n" + "\n" + "Please use this OTP to complete your verification process.\n" + "\n" + "If you did not request this OTP, please ignore this email.\n" + "\n" + "Thank you,\n" + "Smart Attend");

        return true;
    }

    @PostMapping("/verification/{otp}")
    public boolean verification(@PathVariable String otp, @RequestBody User user) {

        Otp systemOTP = otpRepo.findByOtp(otp);

        if (otp != null) {
            if (systemOTP != null && systemOTP.getOtp().equals(otp)) {
                if ("student".equals(user.getRole())) {
                    Student s = studentRepo.findByEmail(user.getEmail());
                    if (s != null) {
                        s.setStatus(1);
                        studentRepo.save(s);
                        return true;
                    }
                } else if ("teacher".equals(user.getRole())) {
                    Teacher t = teacherRepo.findByEmail(user.getEmail());
                    if (t != null) {
                        t.setStatus(1);
                        teacherRepo.save(t);
                        return true;
                    }
                } else if ("org".equals(user.getRole())) {
                    Organization org = organizationRepo.findByEmail(user.getEmail());
                    if (org != null) {
                        org.setStatus(1);
                        organizationRepo.save(org);
                        return true;
                    }
                }
            }
        } else {
            System.out.println("Session is null in verification method");
        }
        return false;
    }


}
