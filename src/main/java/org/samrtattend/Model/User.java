package org.samrtattend.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Getter @Setter @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private Date date;
    private String org;
    private String _class;
    private String role;
    private int status;

    public User(int id, String name, String email, String org, String _class, String role, int status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.org = org;
        this._class = _class;
        this.role = role;
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", org='" + org + '\'' +
                ", _class='" + _class + '\'' +
                ", role='" + role + '\'' +
                ", status=" + status +
                '}';
    }
}
