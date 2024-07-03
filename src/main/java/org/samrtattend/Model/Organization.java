package org.samrtattend.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "organization")
@Getter
@Setter
@NoArgsConstructor
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int code;
    private String email;
    private String password;
    private int status;

    public Organization(int id, String name, int code, String email, int status) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.email = email;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "orgName='" + name + '\'' +
                ", orgCode=" + code +
                ", email='" + email + '\'' +
                '}';
    }
}
