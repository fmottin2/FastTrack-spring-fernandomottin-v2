package br.com.fasttrack.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableSpringDataWebSupport
public class FasttrackApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FasttrackApiApplication.class, args);
	}

}
