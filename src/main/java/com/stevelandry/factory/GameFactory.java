package com.stevelandry.factory;

import com.stevelandry.model.FootballGame;
import com.stevelandry.model.Game;
import com.stevelandry.model.Team;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;

@Component
public class GameFactory {

    public Game createFootballGame(String homeTeamName, String awayTeamName) {
        Team homeTeam = Team.builder().name(homeTeamName).goals(new LinkedHashSet<>()).build();
        Team awayTeam = Team.builder().name(awayTeamName).goals(new LinkedHashSet<>()).build();
        return new FootballGame(homeTeam, awayTeam);
    }
}
