package com.kadzys.VBooks;

import com.kadzys.VBooks.controllers.auth.RegisterRequest;
import com.kadzys.VBooks.models.Role;
import com.kadzys.VBooks.models.User;
import com.kadzys.VBooks.services.AuthenticationService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class VirtualBookRepo {

	final AuthenticationService authenticationService;

	public static void main(String[] args) {
		SpringApplication.run(VirtualBookRepo.class, args);
	}

	@PostConstruct
	public void init() {
		RegisterRequest admin = RegisterRequest.builder()
				.name("admin")
				.password("1234")
				.role(Role.ROLE_ADMIN)
				.build();
		authenticationService.register(admin);
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(source);
	}
}
