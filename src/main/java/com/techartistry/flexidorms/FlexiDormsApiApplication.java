package com.techartistry.flexidorms;

import com.techartistry.flexidorms.security_management.domain.enums.ERole;
import com.techartistry.flexidorms.security_management.infrastructure.repositories.IRoleRepository;
import com.techartistry.flexidorms.shared.util.Utilities;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlexiDormsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlexiDormsApiApplication.class, args);
	}

	/**
	 * Bean que se encarga de insertar los roles por defecto
	 * @param roleRepository Repositorio de roles
	 */
	@Bean
	CommandLineRunner initDatabase(IRoleRepository roleRepository) {
		return args -> {
			Utilities.insertRoleIfNotFound(roleRepository, ERole.ROLE_USER);
			Utilities.insertRoleIfNotFound(roleRepository, ERole.ROLE_ADMIN);
		};
	}
}
