package com.stevelandry.service;

import com.stevelandry.util.GameStatePatternMatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandService {

    private final GameStatePatternMatcher gameStatePatternMatcher;
    private final GameService gameService;

    public void parseInput(final String input) {

        if (gameStatePatternMatcher.isStartGamePattern(input)) {
            String homeTeamName = gameStatePatternMatcher.getHomeTeam(input);
            String awayTeamName = gameStatePatternMatcher.getAwayTeam(input);
            gameService.startGame(homeTeamName, awayTeamName);
        } else if (gameStatePatternMatcher.isGoalPattern(input)) {
            gameService.registerScore(gameStatePatternMatcher.getScoreUpdate(input));
        } else if (gameStatePatternMatcher.isEndGame(input)) {
            gameService.endGame();
        } else if (gameStatePatternMatcher.isPrintGame(input)) {
            gameService.printGame();
        } else {
            if(gameService.isGameInProgress()) {
                throw new IllegalArgumentException("input error - please type 'print' for game details");
            } else {
                throw new IllegalArgumentException("input error - please start a game through typing 'Start:" +
                        "'<Name of Home Team>' vs. '<Name of Away Team>");
            }
        }

    }


}
