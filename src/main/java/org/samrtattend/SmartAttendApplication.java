package org.samrtattend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
@SpringBootApplication
public class SmartAttendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartAttendApplication.class, args);
	}

}
