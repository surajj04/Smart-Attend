package org.samrtattend.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    private String _class;
    private String attend;
    private String org;

    public Attendance(int id, Date date, String _class, String attend, String org) {
        this.id = id;
        this.date = date;
        this._class = _class;
        this.attend = attend;
        this.org = org;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", date=" + date +
                ", _class='" + _class + '\'' +
                ", attend='" + attend + '\'' +
                ", org='" + org + '\'' +
                '}';
    }
}
