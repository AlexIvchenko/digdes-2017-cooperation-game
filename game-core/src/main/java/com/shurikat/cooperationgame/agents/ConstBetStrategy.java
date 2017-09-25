package com.shurikat.cooperationgame.agents;

import com.shurikat.cooperationgame.core.Bet;
import com.shurikat.cooperationgame.core.BetStrategy;

/**
 * @author Alex Ivchenko
 */
public final class ConstBetStrategy implements BetStrategy {
    private final Bet bet;

    public ConstBetStrategy(Bet bet) {
        this.bet = bet;
    }

    @Override
    public Bet bet() {
        return bet;
    }

    @Override
    public String name() {
        return "const";
    }
}
