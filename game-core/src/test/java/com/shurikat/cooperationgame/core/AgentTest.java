package com.shurikat.cooperationgame.core;

import org.junit.Test;

import static com.shurikat.cooperationgame.Utils.agent;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Alex Ivchenko
 */
public class AgentTest {
    @Test
    public void givenTwoEqualByFieldAgents_whenEqualsCall_thenAgentsUnequal() throws Exception {
        Agent a = agent("first", 10, Bet.ZERO);
        Agent b = agent("first", 10, Bet.ZERO);
        assertNotEquals(a, b);
    }

    @Test
    public void equalsAndHashCodeOverridden() throws Exception {
        Agent a = agent("first", 10, Bet.ZERO);
        Agent b = a;
        assertTrue(a.equals(b));
        assertTrue(a.hashCode() == b.hashCode());
    }
}