package com.example.football_standings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandingsResponse {

    private String countryName;

    private String leagueId;

    private String leagueName;

    private String teamId;

    private String teamName;

    private String overallLeaguePosition;
}