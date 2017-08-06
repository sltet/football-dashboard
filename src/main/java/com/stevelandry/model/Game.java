package com.stevelandry.model;

import lombok.Data;

import java.util.List;

@Data
public class Game {

    private static final int MINUTES_INDEX = 0;
    private static final int TEAM_NAME_INDEX = 1;
    private static final int SCORER_NAME_INDEX = 2;

    public enum GameType {
        FOOTBALL
    }

    protected Team homeTeam;
    protected Team awayTeam;
    protected GameType type;

    public void registerScore(List<String> scoreUpdate) {
        Goal goal = Goal.builder().minutes(Integer.parseInt(scoreUpdate.get(MINUTES_INDEX))).scorerName(scoreUpdate.get(SCORER_NAME_INDEX)).build();
        Team scoringTeam = Team.builder().name(scoreUpdate.get(TEAM_NAME_INDEX)).build();
        if(homeTeam.isEqual(scoringTeam)){
            homeTeam.addGoal(goal);
        } else awayTeam.addGoal(goal);
    }

}
