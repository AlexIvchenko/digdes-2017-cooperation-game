package com.shurikat.cooperationgame.core;

import org.junit.Test;

import static com.shurikat.cooperationgame.Utils.agent;
import static com.shurikat.cooperationgame.Utils.anyAgent;
import static org.junit.Assert.assertEquals;

/**
 * @author Alex Ivchenko
 */
public class PartTest {
    @Test
    public void given2OneBets_whenPartExecute_thenBothGet2Coins() throws Exception {
        Agent first = agent("first", 1, Bet.ONE);
        Agent second = agent("second", 1, Bet.ONE);
        Part part = new Part(first, second);
        part.execute();

        assertEquals(2, first.money());
        assertEquals(2, second.money());
    }

    @Test(expected = IllegalStateException.class)
    public void givenTwoExecution_whenPartExecute_thenException() throws Exception {
        Part part = new Part(anyAgent(), anyAgent());
        part.execute();
        part.execute();
    }

    @Test
    public void given2ZeroBets_whenPartExecute_thenBothMiss1Coin() throws Exception {
        Agent first = agent("first", 1, Bet.ZERO);
        Agent second = agent("second", 1, Bet.ZERO);
        Part part = new Part(first, second);
        part.execute();

        assertEquals(0, first.money());
        assertEquals(0, second.money());
    }

    @Test
    public void givenOneBetAndZeroBet_whenPartExecute_thenFirstMiss1CoinSecondGet3Coins() throws Exception {
        Agent first = agent("first", 1, Bet.ONE);
        Agent second = agent("second", 1, Bet.ZERO);
        Part part = new Part(first, second);
        part.execute();

        assertEquals(-1, first.money());
        assertEquals(4, second.money());
    }
}