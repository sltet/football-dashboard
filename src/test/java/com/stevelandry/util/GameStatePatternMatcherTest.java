package com.stevelandry.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class GameStatePatternMatcherTest {

    private static final String HOME_TEAM_NAME = "England";
    private static final String AWAY_TEAM_NAME = "West Germany";
    private static final String START_GAME_INPUT = "Start: '"+ HOME_TEAM_NAME + "' vs. '" + AWAY_TEAM_NAME + "'";
    private static final String MALFORMED_START_GAME_INPUT = "Start: 'England' vs. ";
    private static final String GOAL_INPUT = "11 'West Germany' Haller";
    private static final String PRINT_INPUT = "print";
    private static final String END_INPUT = "End";

    @InjectMocks
    private GameStatePatternMatcher gameStatePatternMatcher;

    @Test
    public void willReturnTrueGivenStartGameInput(){
        boolean isStartGame = gameStatePatternMatcher.isStartGamePattern(START_GAME_INPUT);
        Assert.assertTrue("start game pattern", isStartGame);
    }

    @Test
    public void willReturnFalseGivenStartGameInput(){
        boolean isStartGame = gameStatePatternMatcher.isStartGamePattern(MALFORMED_START_GAME_INPUT);
        Assert.assertFalse("start game pattern", isStartGame);
    }

    @Test
    public void willReturnHomeTeamNameGivenStartGameInput(){
        String homeTeamName = gameStatePatternMatcher.getHomeTeam(START_GAME_INPUT);
        Assert.assertEquals("home team name", HOME_TEAM_NAME, homeTeamName);
    }

    @Test
    public void willReturnAwayTeamNameGivenStartGameInput(){
        String awayTeamName = gameStatePatternMatcher.getAwayTeam(START_GAME_INPUT);
        Assert.assertEquals("away team name", AWAY_TEAM_NAME, awayTeamName);
    }

    @Test
    public void willReturnScoreUpdateListGivenGoalInput(){
        List<String> expectedScoreUpdateList = Arrays.asList("11", AWAY_TEAM_NAME, "Haller");
        List<String> scoreUpdateList = gameStatePatternMatcher.getScoreUpdate(GOAL_INPUT);
        Assert.assertEquals("score update list size", expectedScoreUpdateList.size(), scoreUpdateList.size());
        Assert.assertEquals("score update list", expectedScoreUpdateList, scoreUpdateList);
    }

    @Test
    public void willReturnTrueGivenGoalInput(){
        boolean isGoal = gameStatePatternMatcher.isGoalPattern(GOAL_INPUT);
        Assert.assertTrue("goal pattern", isGoal);
    }

    @Test
    public void willReturnTrueGivenPrintInput(){
        boolean isPrintGame = gameStatePatternMatcher.isPrintGame(PRINT_INPUT);
        Assert.assertTrue("print pattern", isPrintGame);
    }

    @Test
    public void willReturnTrueGivenEndInput(){
        boolean isEndGame = gameStatePatternMatcher.isEndGame(END_INPUT);
        Assert.assertTrue("end game pattern", isEndGame);
    }
}
