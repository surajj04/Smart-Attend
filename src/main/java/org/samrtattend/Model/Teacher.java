package org.samrtattend.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "teachers")
@Getter @Setter @NoArgsConstructor
public class Teacher {

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

    public Teacher(int id, String name, String email, String org, String _class, int status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.org = org;
        this._class = _class;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", org='" + org + '\'' +
                ", _class='" + _class + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
