package com.digital_medical.DigitalMedical;

import com.digital_medical.DigitalMedical.role.RoleConstant;
import com.digital_medical.DigitalMedical.role.RoleEntity;
import com.digital_medical.DigitalMedical.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DigitalMedicalApplication implements CommandLineRunner {

	@Autowired
	private RoleService roleService;

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.basePackage("com.digital_medical.DigitalMedical"))
				.build();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE");
			}
		};
	}

	public static void main(String[] args) {

		SpringApplication.run(DigitalMedicalApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		if (roleService.findAll().isEmpty()) {
			roleService.saveOrUpdate(new RoleEntity(RoleConstant.DOCTOR.toString()));
			roleService.saveOrUpdate(new RoleEntity(RoleConstant.PATIENT.toString()));
		}
	}


	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
