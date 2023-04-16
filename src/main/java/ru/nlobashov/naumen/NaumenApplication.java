package ru.nlobashov.naumen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class NaumenApplication {

	public static void main(String[] args) {
		SpringApplication.run(NaumenApplication.class, args);
	}

}
