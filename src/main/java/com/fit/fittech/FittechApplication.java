package com.fit.fittech;

import com.fit.fittech.models.FitUser;
import com.fit.fittech.models.Role;
import com.fit.fittech.services.FitUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class FittechApplication {

	public static void main(String[] args) {
		SpringApplication.run(FittechApplication.class, args);
	}

	@Bean
	CommandLineRunner run(FitUserService fitUserService) {
		return args -> {
			fitUserService.saveRole(new Role(null, "ROLE_USER"));
			fitUserService.saveRole(new Role(null, "ROLE_MANAGER"));
			fitUserService.saveRole(new Role(null, "ROLE_ADMIN"));
			fitUserService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			fitUserService.saveUser(new FitUser(null, "Zaid Athar", "z@gmail.com", "1234", new ArrayList<>()));
			fitUserService.saveUser(new FitUser(null, "Yash Shekar", "y@gmail.com", "1234", new ArrayList<>()));
			fitUserService.saveUser(new FitUser(null, "Rahul Sevag", "r@gmail.com", "1234", new ArrayList<>()));
			fitUserService.saveUser(new FitUser(null, "Jayesh Vaghela", "j@gmail.com", "1234", new ArrayList<>()));

			fitUserService.addRoleToUser("z@gmail.com", "ROLE_USER");
			fitUserService.addRoleToUser("z@gmail.com", "ROLE_MANAGER");
			fitUserService.addRoleToUser("y@gmail.com", "ROLE_MANAGER");
			fitUserService.addRoleToUser("r@gmail.com", "ROLE_ADMIN");
			fitUserService.addRoleToUser("j@gmail.com", "ROLE_ADMIN");
			fitUserService.addRoleToUser("j@gmail.com", "ROLE_SUPER_ADMIN");
			fitUserService.addRoleToUser("j@gmail.com", "ROLE_USER");

		};
	}
}
