package com.example.football_standings.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.football_standings.config.ApiConfig;
import com.example.football_standings.dto.ApiStandingsResponse;
import com.example.football_standings.dto.StandingsResponse;
import com.example.football_standings.exception.ApiException;

@ExtendWith(MockitoExtension.class)
public class FootballServiceImplTest {

    @Mock
    private ApiConfig apiConfig;
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private FootballServiceImpl footballService;

    private String leagueId = "123";

    @BeforeEach
    public void setUp() {
        when(apiConfig.getBaseUrl()).thenReturn("http://example.com");
        when(apiConfig.getApiKey()).thenReturn("API_KEY");
    }

    @Test
    public void testGetStandings_Success() {
        ApiStandingsResponse[] mockResponse = new ApiStandingsResponse[1];
        ApiStandingsResponse response = new ApiStandingsResponse();
        mockResponse[0] = response;

        ResponseEntity<ApiStandingsResponse[]> restResponse = new ResponseEntity<>(mockResponse, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(ApiStandingsResponse[].class)))
                .thenReturn(restResponse);

        List<StandingsResponse> result = footballService.getStandings(leagueId);

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetStandings_ApiException() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(ApiStandingsResponse[].class)))
                .thenThrow(new RuntimeException("API call failed"));

        ApiException exception = assertThrows(ApiException.class, () -> {
            footballService.getStandings(leagueId);
        });
        assertEquals("Unable to fetch standings data.", exception.getMessage());
    }
}