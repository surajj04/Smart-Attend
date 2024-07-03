package org.samrtattend.Repository;

import org.samrtattend.Model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepo extends JpaRepository<Otp, Integer> {

    Otp findByOtp(String otp);
}
