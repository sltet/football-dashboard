package com.stevelandry.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GameStatePatternMatcher {


    public boolean isStartGamePattern(String input){
        return input.matches(GameConstants.START_GAME_PATTERN);
    }

    public String getHomeTeam(String input) {
        Pattern homeTeamPattern = Pattern.compile(GameConstants.HOME_TEAM_PATTERN);
        Matcher matcher = homeTeamPattern.matcher(input);
        matcher.find();
        return matcher.group(1);
    }

    public String getAwayTeam(String input) {
        Pattern awayTeamPattern = Pattern.compile(GameConstants.AWAY_TEAM_PATTERN);
        Matcher matcher = awayTeamPattern.matcher(input);
        matcher.find();
        return matcher.group(1);
    }

    public List<String> getScoreUpdate(String input) throws IllegalStateException {
        List<String> result = new ArrayList<>();
        Matcher matcher = Pattern.compile(GameConstants.GOAL_PATTERN).matcher(input);
        matcher.find();
        result.add(matcher.group(1));
        result.add(matcher.group(2));
        result.add(matcher.group(3));

        return result;
    }

    public boolean isGoalPattern(String input){
        return input.matches(GameConstants.GOAL_PATTERN);
    }

    public boolean isEndGame(String input){
        return input.matches(GameConstants.END_GAME);
    }

    public boolean isPrintGame(String input){
        return input.matches(GameConstants.PRINT_GAME);
    }

}
