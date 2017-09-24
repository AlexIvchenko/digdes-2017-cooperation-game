package com.shurikat.cooperationgame.core;

/**
 * @author Alex Ivchenko
 */
public abstract class AppliedAgent {
    private final Agent owner;
    private int money;

    public AppliedAgent(Agent owner) {
        this.owner = owner;
    }

    public AppliedAgent(Agent owner, int money) {
        this.owner = owner;
        this.money = money;
    }

    public abstract Bet bet();

    public final boolean hasMoney() {
        return money > 0;
    }

    public final int money() {
        return money;
    }

    public final void reward(Reward reward) {
        money = reward.affect(money);
    }

    public final Agent agent() {
        return owner;
    }
}
