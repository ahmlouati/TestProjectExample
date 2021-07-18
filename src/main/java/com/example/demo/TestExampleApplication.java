package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TestExampleApplication {
    
    public static String getNewPrettyPrintJson() {
        return """
               {
                    "firstName": "Piotr",
                    "lastName": "Mińkowski"
               }
               """;
    }

	public static void main(String[] args) {
	    System.out.println(getNewPrettyPrintJson());
		SpringApplication.run(TestExampleApplication.class, args);
	}

}
