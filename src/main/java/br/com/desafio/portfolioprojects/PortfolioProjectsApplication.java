package br.com.desafio.portfolioprojects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PortfolioProjectsApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(PortfolioProjectsApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(PortfolioProjectsApplication.class, args);
	}
}
