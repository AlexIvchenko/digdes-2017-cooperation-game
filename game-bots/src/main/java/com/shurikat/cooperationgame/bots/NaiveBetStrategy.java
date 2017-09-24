package com.shurikat.cooperationgame.bots;

import com.shurikat.cooperationgame.botapi.BetStrategy;
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
