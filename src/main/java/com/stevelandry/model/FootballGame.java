package com.stevelandry.model;

import lombok.Data;

@Data
public class FootballGame extends Game {

    public FootballGame(Team homeTeam, Team awayTeam) {
        this.setAwayTeam(awayTeam);
        this.setHomeTeam(homeTeam);
        this.setType(GameType.FOOTBALL);
    }

    @Override
    public String toString() {
        if (this.homeTeam != null && awayTeam != null) {
            return String.format("%s %s %s vs. %s %s %s", homeTeam.getName(), homeTeam.getGoals().size(), homeTeam.printGoals() , awayTeam.getName(), awayTeam.getGoals().size(), awayTeam.printGoals() );
        } else {
            return "";
        }
    }
}
