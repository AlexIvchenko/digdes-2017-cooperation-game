package com.shurikat.cooperationgame.core;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.shurikat.cooperationgame.Utils.agent;
import static com.shurikat.cooperationgame.Utils.anyAgent;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Alex Ivchenko
 */
public class RoundTest {
    @Test(expected = IllegalStateException.class)
    public void givenTwoExecution_whenRoundExecute_thenException() throws Exception {
        Round round = Round.builder().addAgent(anyAgent()).addAgent(anyAgent()).build();
        round.execute();
        round.execute();
    }

    @Test
    public void givenNZeroAgents_whenRoundExecute_thenCorrectBalance() throws Exception {
        Set<Agent> agents = new HashSet<>();
        final int n = 100;
        final int money = 1;
        for (int i = 0; i < n; ++i) {
            agents.add(agent("" + i, money, Bet.ZERO));
        }
        Round.builder().addAll(agents).build().execute();
        assertTrue(agents.stream().allMatch(agent -> agent.money() == money + (n - 1) * (-1)));
    }

    @Test
    public void givenNOneAgents_whenRoundExecute_thenCorrectBalance() throws Exception {
        Set<Agent> agents = new HashSet<>();
        final int n = 100;
        final int money = 1;
        for (int i = 0; i < n; ++i) {
            agents.add(agent("" + i, money, Bet.ONE));
        }
        Round.builder().addAll(agents).build().execute();
        assertTrue(agents.stream().allMatch(agent -> agent.money() == money + (n - 1) * 2 - 1 * (n - 1)));
    }

    @Test
    public void givenOneZeroAgentAnfTwoOneAgents_whenRoundExecute_thenCorrectBalance() throws Exception {
        Agent z1 = agent("z1", 1, Bet.ZERO);
        Agent o1 = agent("o1", 1, Bet.ONE);
        Agent o2 = agent("o2", 1, Bet.ONE);
        Round.builder()
                .addAgent(z1)
                .addAgent(o1)
                .addAgent(o2)
                .build()
                .execute();
        assertEquals(7, z1.money());
        assertEquals(0, o1.money());
        assertEquals(0, o2.money());
    }
}