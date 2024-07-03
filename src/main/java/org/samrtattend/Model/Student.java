package org.samrtattend.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private Date date;
    private String org;
    private String _class;
    private String password;
    private int status;

    public Student() {
    }

    public Student(int id, String name, String email, String org, String _class, String password, int status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.org = org;
        this._class = _class;
        this.password = password;
        this.status = status;
    }


    @Override
    public String toString() {
        return "Student{" +
                "_class='" + _class + '\'' +
                ", org='" + org + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}
