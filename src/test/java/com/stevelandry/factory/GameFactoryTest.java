package com.stevelandry.factory;

import com.stevelandry.model.FootballGame;
import com.stevelandry.model.Game;
import com.stevelandry.model.Team;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashSet;

@RunWith(SpringRunner.class)
public class GameFactoryTest {

    private String homeTeamName = "Red Devils";
    private String awayTeamName = "Red Bulls";

    @InjectMocks
    private GameFactory gameFactory;

    @Test
    public void willCreateFootballGameGivenHomeTeamNameAndAwayTeamName(){
        Team homeTeam = Team.builder().name(homeTeamName).goals(new LinkedHashSet<>()).build();
        Team awayTeam = Team.builder().name(awayTeamName).goals(new LinkedHashSet<>()).build();
        Game expected = new FootballGame(homeTeam, awayTeam);
        Game actual = gameFactory.createFootballGame(homeTeamName, awayTeamName);
        Assert.assertEquals(expected, actual);
    }
}
