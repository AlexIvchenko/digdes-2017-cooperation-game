package com.shurikat.cooperationgame.agents;

import com.shurikat.cooperationgame.core.BetStrategy;
import com.shurikat.cooperationgame.core.Bet;

/**
 * @author Alex Ivchenko
 */
public final class GreedyBetStrategy implements BetStrategy {
    @Override
    public Bet bet() {
        return Bet.ZERO;
    }

    @Override
    public String name() {
        return "greedy";
    }
}
