package com.example.football_standings.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.football_standings.config.ApiConfig;
import com.example.football_standings.dto.StandingsResponse;
import com.example.football_standings.exception.ApiException;
import com.example.football_standings.service.FootballService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Football Standings API", description = "Endpoints for fetching football standings by country, league, and team")
public class FootballStandingsController {

	@Autowired
	private FootballService footballService;

	@Autowired
	private ApiConfig apiConfig;

	@Operation(summary = "Get Football Standings", description = "Fetch football standings using league id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully retrieved standings"),
			@ApiResponse(responseCode = "503", description = "Service unavailable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))) })
	@GetMapping("/standings/{leagueId}")
	public ResponseEntity<List<StandingsResponse>> getStandings(@PathVariable String leagueId) {
		try {
			List<StandingsResponse> standings = footballService.getStandings(leagueId);
			return ResponseEntity.ok(standings);
		} catch (ApiException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Collections.singletonList(new StandingsResponse("Error", "N/A", "N/A", "N/A", "N/A", "N/A")));
		}
	}

	@Operation(summary = "Update Offline Mode", description = "Update the offline mode status of the API. Set to true to enable offline mode, false to disable it.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully updated offline mode", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))) })
	@PutMapping("/offline-mode/{status}")
	public ResponseEntity<Map<String, Object>> updateOfflineMode(@PathVariable boolean status) {
		apiConfig.setOffline(status);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(Collections.singletonMap("Offline mode updated to: ", apiConfig.isOffline()));
	}

}
