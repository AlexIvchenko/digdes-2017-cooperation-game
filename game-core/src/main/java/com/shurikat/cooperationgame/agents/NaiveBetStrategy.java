package com.shurikat.cooperationgame.agents;

import com.shurikat.cooperationgame.core.BetStrategy;
import com.shurikat.cooperationgame.core.Bet;

/**
 * @author Alex Ivchenko
 */
public final class NaiveBetStrategy implements BetStrategy {
    @Override
    public Bet bet() {
        return Bet.ONE;
    }

    @Override
    public String name() {
        return "naive";
    }
}
