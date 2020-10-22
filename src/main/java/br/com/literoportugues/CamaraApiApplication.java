package br.com.literoportugues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CamaraApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamaraApiApplication.class, args);
	}
	

}
