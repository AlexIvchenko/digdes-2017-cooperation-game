package com.shurikat.cooperationgame.core;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public abstract class Agent {
    private final UUID id = UUID.randomUUID();
    private final String name;
    private int money;

    public Agent(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public abstract Bet bet();

    public final String name() {
        return name;
    }

    public final boolean hasMoney() {
        return money > 0;
    }

    public final int money() {
        return money;
    }

    public final void reward(Reward reward) {
        money = reward.affect(money);
    }

    @Override
    public String toString() {
        return "Agent(name = " + name() + ")";
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Agent agent = (Agent) o;

        return id.equals(agent.id);
    }

    @Override
    public final int hashCode() {
        return id.hashCode();
    }
}
