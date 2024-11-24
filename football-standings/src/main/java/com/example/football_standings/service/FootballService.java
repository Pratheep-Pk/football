package com.example.football_standings.service;

import java.util.List;

import com.example.football_standings.dto.StandingsResponse;

public interface FootballService {
	List<StandingsResponse>  getStandings(String leagueId);
}
