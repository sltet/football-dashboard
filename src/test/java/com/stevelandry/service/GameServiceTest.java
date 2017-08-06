package com.stevelandry.service;

import com.stevelandry.factory.GameFactory;
import com.stevelandry.model.CurrentGame;
import com.stevelandry.model.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class GameServiceTest {

    @Mock
    private CurrentGame currentGame;

    @Mock
    private Game game;

    @Mock
    private GameFactory gameFactory;

    private String homeTeamName = "Red Devils";
    private String awayTeamName = "Red Bulls";

    @InjectMocks
    private GameService gameService;

    @Test
    public void willStartNewGameGivenHomeTeamAndAwayTeam(){
        Mockito.when(currentGame.isGamePlaying()).thenReturn(false);
        Mockito.when(gameFactory.createFootballGame(homeTeamName, awayTeamName)).thenReturn(game);
        gameService.startGame(homeTeamName, awayTeamName);
        Mockito.verify(currentGame, Mockito.times(1)).setGame(game);
    }

    @Test
    public void willNotStartNewGameIfCurrentGameBeingPlayed(){
        Mockito.when(currentGame.isGamePlaying()).thenReturn(true);
        gameService.startGame(homeTeamName, awayTeamName);
        Mockito.verify(gameFactory, Mockito.times(0)).createFootballGame(homeTeamName, awayTeamName);
    }

    @Test
    public void willEndGameIfCurrentGameBeingPlayed(){
        Mockito.when(currentGame.isGamePlaying()).thenReturn(true);
        gameService.endGame();
        Mockito.verify(currentGame, Mockito.times(1)).endGame();
    }

    @Test
    public void willNotEndGameIfCurrentGameNotBeingPlayed(){
        Mockito.when(currentGame.isGamePlaying()).thenReturn(false);
        gameService.endGame();
        Mockito.verify(currentGame, Mockito.times(0)).endGame();
    }

    @Test
    public void willPrintGameIfCurrentGameBeingPlayed(){
        Mockito.when(currentGame.isGamePlaying()).thenReturn(true);
        gameService.printGame();
        Mockito.verify(currentGame, Mockito.times(1)).printGame();
    }

    @Test
    public void willNotPrintGameIfCurrentGameNotBeingPlayed(){
        Mockito.when(currentGame.isGamePlaying()).thenReturn(false);
        gameService.printGame();
        Mockito.verify(currentGame, Mockito.times(0)).printGame();
    }

    @Test
    public void willRegisterScoreIfCurrentGameBeingPlayed(){
        List<String> scoreUpdate = new ArrayList<>();
        Mockito.when(currentGame.isGamePlaying()).thenReturn(true);
        gameService.registerScore(scoreUpdate);
        Mockito.verify(currentGame, Mockito.times(1)).registerScore(scoreUpdate);
    }

    @Test
    public void willNotRegisterScoreIfCurrentGameNotBeingPlayed(){
        List<String> scoreUpdate = new ArrayList<>();
        Mockito.when(currentGame.isGamePlaying()).thenReturn(false);
        gameService.registerScore(scoreUpdate);
        Mockito.verify(currentGame, Mockito.times(0)).registerScore(scoreUpdate);
    }
}
