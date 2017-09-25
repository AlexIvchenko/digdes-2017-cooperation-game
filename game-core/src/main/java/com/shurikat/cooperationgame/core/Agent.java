package com.shurikat.cooperationgame.core;

import java.util.Objects;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public final class Agent {
    private final UUID id = UUID.randomUUID();
    private final String name;
    private final BetStrategy strategy;
    private int money;

    public static BetStrategyStageBuilder builder() {
        return new BuilderImpl();
    }

    public Bet bet() {
        Bet bet = strategy.bet();
        money = bet.affect(money);
        return bet;
    }

    public String name() {
        return name;
    }

    public boolean hasMoney() {
        return money > 0;
    }

    public int money() {
        return money;
    }

    public final void reward(Reward reward) {
        money = reward.affect(money);
    }

    public final BetStrategy strategy() {
        return strategy;
    }

    @Override
    public final String toString() {
        return String.format("Bot(name = %s, strategy = %s, money = %s)", name(), strategy.name(), money());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Agent agent = (Agent) o;

        return id.equals(agent.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    private Agent(String name, BetStrategy strategy, int money) {
        this.name = name;
        this.strategy = strategy;
        this.money = money;
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
        public Agent money(int money) {
            this.money = money;
            return new Agent(name, strategy, this.money);
        }
    }

    public interface BetStrategyStageBuilder {
        NameStageBuilder strategy(BetStrategy strategy);
    }

    public interface NameStageBuilder {
        MoneyStageBuilder name(String name);
    }

    public interface MoneyStageBuilder {
        Agent money(int startMoney);
    }
}
