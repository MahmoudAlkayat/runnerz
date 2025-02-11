package dev.mahmoudalkayat.runnerz;
//Never put code outside the main package
//Inside the default package because Spring
//Won't know what to do with it


import dev.mahmoudalkayat.runnerz.run.Location;
import dev.mahmoudalkayat.runnerz.run.Run;
import dev.mahmoudalkayat.runnerz.run.RunRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.Duration;

@SpringBootApplication(scanBasePackages = "dev.mahmoudalkayat.runnerz")
// @EnableJpaRepositories("dev.mahmoudalkayat.runnerz.run") 
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}

	@Bean
	CommandLineRunner runner(RunRepository runRepository){
		return args -> {
			Run run = new Run (1,"First Run", LocalDateTime.now(), LocalDateTime.now().plus(Duration.ofHours(1)),5, Location.OUTDOOR);
			runRepository.create(run);
		};
	}

}

/*
Spring MVC
Model: The thing we are working with
View: How we are representing the model. Sometimes we use HTML to display this
but we will be displaying the data using JSON
Controller: The thing that handles requests. It takes a request. figures out
what to do with it. delegate tasks to classes. return response
 */
