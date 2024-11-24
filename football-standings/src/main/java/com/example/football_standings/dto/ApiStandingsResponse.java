package com.example.football_standings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiStandingsResponse {
	
    private String country_name;
    
    private String league_id;
    
    private String league_name;
    
    private String team_id;
    
    private String team_name;
    
    private String overall_league_position;
    
}
