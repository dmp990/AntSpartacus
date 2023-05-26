package com.sparta.as.antsparticans;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class AntSparticansApplication {

	Logger logger = Logger.getLogger("logger-main");
	public static void main(String[] args) {

		SpringApplication.run(AntSparticansApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner() {
		return args -> logger.log(Level.SEVERE, "from main");
	}

}
