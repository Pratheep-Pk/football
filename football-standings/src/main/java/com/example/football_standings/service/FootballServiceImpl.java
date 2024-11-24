package com.example.football_standings.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.football_standings.config.ApiConfig;
import com.example.football_standings.dto.ApiStandingsResponse;
import com.example.football_standings.dto.StandingsResponse;
import com.example.football_standings.exception.ApiException;

@Service
public class FootballServiceImpl implements FootballService {

	private static final Logger log = LoggerFactory.getLogger(FootballServiceImpl.class);

	@Autowired
	private ApiConfig apiConfig;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<StandingsResponse> getStandings(String leagueId) throws ApiException {

		if (apiConfig.isOffline()) {
			return getOfflineStandings();
		}
		
		String url = String.format("%s?action=get_standings&league_id=%s&APIkey=%s", apiConfig.getBaseUrl(), leagueId,
				apiConfig.getApiKey());

		try {

			ResponseEntity<ApiStandingsResponse[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
					ApiStandingsResponse[].class);
			List<ApiStandingsResponse> standingsList = Arrays.asList(responseEntity.getBody());

			if (standingsList == null || standingsList.isEmpty()) {
				throw new ApiException("No standings data found in API response.");
			}
			List<StandingsResponse> standings = StandingsMapper.mapToStandingsResponseList(standingsList);

			return standings;

		} catch (Exception e) {
			log.error("Error fetching standings data", e);
			throw new ApiException("Unable to fetch standings data.", e);
		}
	}

	private List<StandingsResponse> getOfflineStandings() {
		return Collections
				.singletonList(new StandingsResponse("Offline", "N/A", "Offline League", "N/A", "N/A", "N/A"));
	}

}
