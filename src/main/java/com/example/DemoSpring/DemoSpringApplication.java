package com.example.DemoSpring;

import com.example.DemoSpring.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableJpaRepositories
@SpringBootApplication
public class DemoSpringApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoSpringApplication.class, args);

		final UserService obj = (UserService) context.getBean("userService");
		obj.catcall();
	}

}
