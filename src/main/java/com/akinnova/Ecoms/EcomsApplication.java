package com.akinnova.Ecoms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebMvc
@Slf4j
public class EcomsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomsApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer webMvcConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				WebMvcConfigurer.super.addCorsMappings(registry);
				registry
						.addMapping("/**")
						.allowedOrigins("*")
						.allowedHeaders("*")
						.allowedMethods("POST", "GET", "PUT", "PATCH", "OPTIONS");
			}
		};
	}

	//	@Bean
//	@Transactional
//	public CommandLineRunner commandLineRunner(UserRepository userRepository){
//		log.info("i am in the commandline runner");
//		return args -> {
//			userRepository.findAll().stream().peek(p-> {
//				p.setActiveStatus(true);
//				userRepository.save(p);
//				System.out.println("I have updated this guy " );
//			}).toList();
//		};
//	}
}
