package com.raunak.springbootdemootp;

import com.raunak.springbootdemootp.configurations.ApplicationProp;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing    // required for auto creation of createdDate, lastModifiedDates
public class SpringbootDemoOtpApplication {
	private static final Logger log = LogManager.getLogger(SpringbootDemoOtpApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoOtpApplication.class, args);

		String dbUrl = ApplicationProp.getInstance().getProperty("spring.datasource.url");
		String dbUsername = ApplicationProp.getInstance().getProperty("spring.datasource.username");
		String dbPassword = ApplicationProp.getInstance().getProperty("spring.datasource.password");

		log.info("DB Configs for Flyway - {}, {}, {}", dbUrl, dbUsername, dbPassword);
		Flyway flyway = Flyway.configure().dataSource(dbUrl, dbUsername, dbPassword).load();
		flyway.migrate();

		log.info("Spring Boot Project Live and Running");
	}

}
