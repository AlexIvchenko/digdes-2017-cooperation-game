package com.shurikat.cooperationgame.botapi;

import com.shurikat.cooperationgame.core.Agent;
import com.shurikat.cooperationgame.core.AppliedAgent;
import com.shurikat.cooperationgame.core.Game;

/**
 * @author Alex Ivchenko
 */
public final class BotAgent implements Agent {
    private final BetStrategy strategy;
    private int startMoney = 0;

    public BotAgent(BetStrategy strategy) {
        this.strategy = strategy;
    }

    public BotAgent(BetStrategy strategy, int startMoney) {
        this.strategy = strategy;
        this.startMoney = startMoney;
    }

    @Override
    public AppliedAgent applyGame(Game game) {
        return new AppliedBotAgent(this, startMoney, strategy);
    }
}
