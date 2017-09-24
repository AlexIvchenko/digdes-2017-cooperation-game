package com.shurikat.cooperationgame.bots;

import com.shurikat.cooperationgame.botapi.BetStrategy;
import com.shurikat.cooperationgame.core.Bet;

/**
 * @author Alex Ivchenko
 */
public class NaiveBetStrategy implements BetStrategy {
    @Override
    public Bet bet() {
        return Bet.ONE;
    }
}
