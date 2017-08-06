package com.stevelandry.service;

import com.stevelandry.factory.GameFactory;
import com.stevelandry.model.CurrentGame;
import com.stevelandry.model.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final CurrentGame currentGame;
    private final GameFactory gameFactory;

    private static final String NO_GAME_CURRENTLY_IN_PROGRESS = "No game currently in progress";
    private static final String GAME_IN_PROGRESS = "There is a game in progress";


    public void startGame(String homeTeamName, String awayTeamName){

        if(isGameInProgress()) {
            System.out.println(GAME_IN_PROGRESS);
        } else {
            Game footballGame = gameFactory.createFootballGame(homeTeamName, awayTeamName);
            currentGame.setGame(footballGame);
        }
    }

    public void endGame(){

        if (!isGameInProgress()) {
            System.out.println(NO_GAME_CURRENTLY_IN_PROGRESS);
        } else {
            currentGame.endGame();
        }

    }

    public void printGame(){
        if (!isGameInProgress()) {
            System.out.println(NO_GAME_CURRENTLY_IN_PROGRESS);
        } else {
            System.out.println(currentGame.printGame());
        }
    }

    public void registerScore(List<String> scoreUpdate) {
        if (!isGameInProgress()) {
            System.out.println(NO_GAME_CURRENTLY_IN_PROGRESS);
        } else {
            currentGame.registerScore(scoreUpdate);
        }
    }

    public boolean isGameInProgress() {
        return currentGame.isGamePlaying();
    }
}
