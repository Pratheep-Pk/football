package com.example.football_standings.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.football_standings.dto.ApiStandingsResponse;
import com.example.football_standings.dto.StandingsResponse;

@Component
public class StandingsMapper {

	  public static StandingsResponse mapToStandingsResponse(ApiStandingsResponse apiResponse) {
	        StandingsResponse response = new StandingsResponse();
	        response.setCountryName(apiResponse.getCountry_name());
	        response.setLeagueId(apiResponse.getLeague_id());
	        response.setLeagueName(apiResponse.getLeague_name());
	        response.setTeamId(apiResponse.getTeam_id());
	        response.setTeamName(apiResponse.getTeam_name());
	        response.setOverallLeaguePosition(apiResponse.getOverall_league_position());
	        return response;
	    }

	    public static List<StandingsResponse> mapToStandingsResponseList(List<ApiStandingsResponse> apiResponseList) {
	        return apiResponseList.stream()
	                .map(StandingsMapper::mapToStandingsResponse)
	                .collect(Collectors.toList());
	    }
}

