package org.samrtattend.Model;

import java.util.Arrays;

// AttendanceRequest.java
public class AttendanceRequest {
    private Integer[] attend;
    private String _class;
    private String org;

    // Getters and setters
    public Integer[] getAttend() {
        return attend;
    }

    public void setAttend(Integer[] attend) {
        this.attend = attend;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    @Override
    public String toString() {
        return "AttendanceRequest{" +
                "attend=" + Arrays.toString(attend) +
                ", _class='" + _class + '\'' +
                ", org='" + org + '\'' +
                '}';
    }
}
