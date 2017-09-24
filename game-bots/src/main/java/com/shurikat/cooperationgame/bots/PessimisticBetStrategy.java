package com.shurikat.cooperationgame.bots;

import com.shurikat.cooperationgame.botapi.BetStrategy;
import com.shurikat.cooperationgame.core.Bet;

/**
 * @author Alex Ivchenko
 */
public final class PessimisticBetStrategy implements BetStrategy {
    @Override
    public Bet bet() {
        return Bet.ZERO;
    }

    @Override
    public String name() {
        return "pessimistic";
    }
}
