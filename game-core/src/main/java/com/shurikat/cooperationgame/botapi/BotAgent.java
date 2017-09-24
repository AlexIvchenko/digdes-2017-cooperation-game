package com.shurikat.cooperationgame.botapi;

import com.shurikat.cooperationgame.core.Agent;
import com.shurikat.cooperationgame.core.AppliedAgent;
import com.shurikat.cooperationgame.core.Game;

import java.util.Objects;

/**
 * @author Alex Ivchenko
 */
public final class BotAgent implements Agent {
    private final String name;
    private final BetStrategy strategy;
    private final int startMoney;

    public static BetStrategyStageBuilder builder() {
        return new BuilderImpl();
    }

    private BotAgent(String name, BetStrategy strategy, int startMoney) {
        this.name = name;
        this.strategy = strategy;
        this.startMoney = startMoney;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public AppliedAgent applyGame(Game game) {
        return new AppliedBotAgent(this, startMoney, strategy);
    }

    @Override
    public String toString() {
        return "I am bot, my name is " + name + ", I use " + strategy.name() + " strategy";
    }

    private static class BuilderImpl implements BetStrategyStageBuilder, NameStageBuilder, MoneyStageBuilder {
        private String name;
        private BetStrategy strategy;
        private int startMoney;
        @Override
        public NameStageBuilder strategy(BetStrategy strategy) {
            Objects.requireNonNull(strategy, "strategy must be not null");
            this.strategy = strategy;
            return this;
        }

        @Override
        public MoneyStageBuilder name(String name) {
            Objects.requireNonNull(name, "name must be not null");
            this.name = name;
            return this;
        }

        @Override
        public BotAgent startMoney(int startMoney) {
            this.startMoney = startMoney;
            return new BotAgent(name, strategy, startMoney);
        }
    }

    public interface BetStrategyStageBuilder {
        NameStageBuilder strategy(BetStrategy strategy);
    }

    public interface NameStageBuilder {
        MoneyStageBuilder name(String name);
    }

    public interface MoneyStageBuilder {
        BotAgent startMoney(int startMoney);
    }
}
