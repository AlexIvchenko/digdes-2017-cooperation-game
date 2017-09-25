package com.shurikat.cooperationgame.botapi;

import com.shurikat.cooperationgame.core.Agent;
import com.shurikat.cooperationgame.core.Bet;

import java.util.Objects;

/**
 * @author Alex Ivchenko
 */
public final class BotAgent extends Agent {
    private final BetStrategy strategy;

    public static BetStrategyStageBuilder builder() {
        return new BuilderImpl();
    }

    private BotAgent(String name, BetStrategy strategy, int money) {
        super(name, money);
        this.strategy = strategy;
    }

    @Override
    public Bet bet() {
        return strategy.bet();
    }

    @Override
    public final String toString() {
        return String.format("Bot(name = %s, strategy = %s)", name(), strategy.name());
    }

    private static class BuilderImpl implements BetStrategyStageBuilder, NameStageBuilder, MoneyStageBuilder {
        private String name;
        private BetStrategy strategy;
        private int money;

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
        public BotAgent money(int money) {
            this.money = money;
            return new BotAgent(name, strategy, this.money);
        }
    }

    public interface BetStrategyStageBuilder {
        NameStageBuilder strategy(BetStrategy strategy);
    }

    public interface NameStageBuilder {
        MoneyStageBuilder name(String name);
    }

    public interface MoneyStageBuilder {
        BotAgent money(int startMoney);
    }
}
