package com.stevelandry.service;

import com.stevelandry.util.GameStatePatternMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class CommandServiceTest {

    @Mock
    private GameStatePatternMatcher gameStatePatternMatcher;

    private String homeTeamName = "Red Devils";
    private String awayTeamName = "Red Bulls";

    @Mock
    private GameService gameService;

    @InjectMocks
    private CommandService commandService;


    @Test
    public void willStartGameGivenStartGameInputPattern(){
        Mockito.when(gameStatePatternMatcher.isStartGamePattern(Mockito.anyString())).thenReturn(true);
        Mockito.when(gameStatePatternMatcher.getHomeTeam(Mockito.anyString())).thenReturn(homeTeamName);
        Mockito.when(gameStatePatternMatcher.getAwayTeam(Mockito.anyString())).thenReturn(awayTeamName);
        commandService.parseInput(Mockito.anyString());
        Mockito.verify(gameService, Mockito.times(1)).startGame(homeTeamName, awayTeamName);
    }

    @Test
    public void willRegisterScoreGivenGoalInputPattern(){
        List<String> scoreUpdate = new ArrayList<>();
        Mockito.when(gameStatePatternMatcher.isGoalPattern(Mockito.anyString())).thenReturn(true);
        Mockito.when(gameStatePatternMatcher.getScoreUpdate(Mockito.anyString())).thenReturn(scoreUpdate);
        commandService.parseInput(Mockito.anyString());
        Mockito.verify(gameService, Mockito.times(1)).registerScore(scoreUpdate);
    }

    @Test
    public void willEndGameGivenEndGameInputPattern(){
        Mockito.when(gameStatePatternMatcher.isEndGame(Mockito.anyString())).thenReturn(true);
        commandService.parseInput(Mockito.anyString());
        Mockito.verify(gameService, Mockito.times(1)).endGame();
    }

    @Test
    public void willPrintGameGivenPrintGameInputPattern(){
        Mockito.when(gameStatePatternMatcher.isPrintGame(Mockito.anyString())).thenReturn(true);
        commandService.parseInput(Mockito.anyString());
        Mockito.verify(gameService, Mockito.times(1)).printGame();
    }

    @Test(expected = IllegalArgumentException.class)
    public void willThrowExceptionWhenInputErrorAndNoGameInProgress(){
        Mockito.when(gameStatePatternMatcher.isStartGamePattern(Mockito.anyString())).thenReturn(false);
        Mockito.when(gameStatePatternMatcher.isGoalPattern(Mockito.anyString())).thenReturn(false);
        Mockito.when(gameStatePatternMatcher.isEndGame(Mockito.anyString())).thenReturn(false);
        Mockito.when(gameStatePatternMatcher.isPrintGame(Mockito.anyString())).thenReturn(false);
        Mockito.when(gameService.isGameInProgress()).thenReturn(false);
        commandService.parseInput(Mockito.anyString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void willThrowExceptionWhenInputErrorAndGameInProgress(){
        Mockito.when(gameStatePatternMatcher.isStartGamePattern(Mockito.anyString())).thenReturn(false);
        Mockito.when(gameStatePatternMatcher.isGoalPattern(Mockito.anyString())).thenReturn(false);
        Mockito.when(gameStatePatternMatcher.isEndGame(Mockito.anyString())).thenReturn(false);
        Mockito.when(gameStatePatternMatcher.isPrintGame(Mockito.anyString())).thenReturn(false);
        Mockito.when(gameService.isGameInProgress()).thenReturn(true);
        commandService.parseInput(Mockito.anyString());
    }
}
