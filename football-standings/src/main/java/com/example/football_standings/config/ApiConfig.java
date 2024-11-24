package com.example.football_standings.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "api.football")
@Data
public class ApiConfig {
	
    private String baseUrl;
    
    private String apiKey;
    
    private boolean offline;
    
}
