package com.football.footballstanding.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.football.footballstanding.constant.Constants.REQUEST_PARAM_ACTION;
import static com.football.footballstanding.constant.Constants.REQUEST_PARAM_API_KEY;
import static com.football.footballstanding.constant.Constants.REQUEST_PARAM_LEAGUE_ID;

@FeignClient(value = "footballApiService", url = "${football-api-service.url}")
public interface FootballApiClient {
    @GetMapping
    Object getStandings(@RequestParam(REQUEST_PARAM_ACTION) String action,
        @RequestParam(REQUEST_PARAM_LEAGUE_ID) Integer leagueId, @RequestParam(REQUEST_PARAM_API_KEY) String apiKey);
}
