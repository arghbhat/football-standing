package com.football.footballstanding.service;

import com.football.footballstanding.domain.Standing;

import java.util.List;

public interface FootBallStandingService {
    List<Standing> getStandings(Integer leagueId);
}
