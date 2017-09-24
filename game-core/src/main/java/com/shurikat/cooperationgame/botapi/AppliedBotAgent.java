package com.shurikat.cooperationgame.botapi;

import com.shurikat.cooperationgame.core.Agent;
import com.shurikat.cooperationgame.core.AppliedAgent;
import com.shurikat.cooperationgame.core.Bet;

/**
 * @author Alex Ivchenko
 */
final class AppliedBotAgent extends AppliedAgent {
    private final BetStrategy strategy;

    AppliedBotAgent(Agent owner, int money, BetStrategy strategy) {
        super(owner, money);
        this.strategy = strategy;
    }

    @Override
    public Bet bet() {
        return strategy.bet();
    }
}
