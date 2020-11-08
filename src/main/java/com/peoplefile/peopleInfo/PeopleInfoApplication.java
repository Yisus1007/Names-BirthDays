package com.peoplefile.peopleInfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.peoplefile.*"})
public class PeopleInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeopleInfoApplication.class, args);
	}

}
