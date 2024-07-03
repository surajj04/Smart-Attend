package org.samrtattend.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "otp")
@Getter @Setter @NoArgsConstructor
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String otp;

    public Otp(String otp) {
        this.otp = otp;
    }


    @Override
    public String toString() {
        return "OTP{" +
                "id=" + id +
                ", otpValue='" + otp + '\'' +
                '}';
    }
}
