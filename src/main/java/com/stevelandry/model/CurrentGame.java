package com.stevelandry.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CurrentGame {

    private Game game;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isGamePlaying(){
        return game != null;
    }

    public void endGame() {
        this.game = null;
    }

    public String printGame() {
        return game.toString();
    }

    public void registerScore(List<String> scoreUpdate) {
        this.game.registerScore(scoreUpdate);
    }
}
