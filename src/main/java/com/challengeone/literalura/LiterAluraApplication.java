package com.challengeone.literalura;

import com.challengeone.literalura.model.API_Search;
import com.challengeone.literalura.service.API_Helper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		String json = API_Helper.request("https://gutendex.com/books/?search=romeo");
		API_Search response = API_Helper.JSON_Object(json, API_Search.class);
		System.out.println(response);
	}
}
