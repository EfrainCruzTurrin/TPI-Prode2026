package com.programacion4tpi.prode;

import com.programacion4tpi.prode.config.jwt.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class ProdeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdeApplication.class, args);
	}

}
