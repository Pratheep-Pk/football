package com.example.football_standings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FootballStandingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballStandingsApplication.class, args);
	}

}
