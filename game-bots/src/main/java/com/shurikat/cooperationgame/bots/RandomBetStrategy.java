package com.shurikat.cooperationgame.bots;

import com.shurikat.cooperationgame.botapi.BetStrategy;
import com.shurikat.cooperationgame.core.Bet;

/**
 * @author Alex Ivchenko
 */
public class RandomBetStrategy implements BetStrategy {
    private final ProbabilityBetStrategy delegate = new ProbabilityBetStrategy(50);
    
    @Override
    public Bet bet() {
        return delegate.bet();
    }
}
