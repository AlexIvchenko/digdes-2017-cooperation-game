package com.shurikat.cooperationgame.core;

import com.shurikat.cooperationgame.summary.GameResult;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.shurikat.cooperationgame.Utils.agent;
import static com.shurikat.cooperationgame.Utils.anyAgent;
import static com.shurikat.cooperationgame.Utils.nullAgent;
import static org.junit.Assert.*;

/**
 * @author Alex Ivchenko
 */
public class GameTest {

    @Test(expected = NullPointerException.class)
    public void givenSetAgentsWithNull_whenGameBuild_thenNPE() throws Exception {
        Set<Agent> agents = new HashSet<>();
        agents.add(anyAgent());
        agents.add(nullAgent());
        Game.builder().addAll(agents);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullAgent_whenGameBuild_thenNPE() throws Exception {
        Game.builder().addAgent(nullAgent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenOnlyOneAgent_whenGameBuild_thenException() throws Exception {
        Agent singleton = agent("singleton", 1000000, Bet.ZERO);
        Game.builder().addAgent(singleton).build();
    }

    @Test
    public void givenTwoZeroAgentsWith1Coin_whenProceed_thenGameIsFinishedBy1RoundAndNobodyIsWinner() throws Exception {
        Agent z1 = agent("z1", 1, Bet.ZERO);
        Agent z2 = agent("z2", 1, Bet.ZERO);
        GameResult result = Game.builder().addAgent(z1).addAgent(z2).build().proceed(1).result();
        assertTrue(result.isFinished());
        assertFalse(result.winner().isPresent());
        assertEquals(0, result.remaining().count());
        assertEquals(0, z1.money());
        assertEquals(0, z2.money());
    }

    @Test
    public void givenTwoOneAgentsWith1Coin_whenProceedManyRounds_thenGameIsNotFinishedAndAllOfThemAreRemaining() throws Exception {
        final int m1 = 1;
        Agent o1 = agent("o1", m1, Bet.ONE);
        final int m2 = 2;
        Agent o2 = agent("o2", m2, Bet.ONE);
        final int rounds = 100;
        GameResult result = Game.builder().addAgent(o1).addAgent(o2).build().proceed(rounds).result();
        assertFalse(result.isFinished());
        assertFalse(result.hasWinner());
        assertFalse(result.winner().isPresent());
        assertEquals(2, result.remaining().count());
        assertEquals(m1 + rounds * 2 - 1 * rounds, o1.money());
        assertEquals(m2 + rounds * 2 - 1 * rounds, o2.money());
    }
}