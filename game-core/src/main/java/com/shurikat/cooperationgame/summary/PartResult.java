package com.shurikat.cooperationgame.summary;

import com.shurikat.cooperationgame.core.Agent;
import com.shurikat.cooperationgame.core.Bet;
import com.shurikat.cooperationgame.core.Reward;

import java.util.Objects;

/**
 * @author Alex Ivchenko
 */
public final class PartResult {
    private final Agent firstAgent;
    private final Agent secondAgent;
    private final Bet firstAgentBet;
    private final Bet secondAgentBet;
    private final Reward firstAgentReward;
    private final Reward secondAgentReward;

    public static Builder builder() {
        return new Builder();
    }

    private PartResult(Agent firstAgent,
                       Agent secondAgent,
                       Bet firstAgentBet,
                       Bet secondAgentBet,
                       Reward firstAgentReward,
                       Reward secondAgentReward) {
        this.firstAgent = firstAgent;
        this.secondAgent = secondAgent;
        this.firstAgentBet = firstAgentBet;
        this.secondAgentBet = secondAgentBet;
        this.firstAgentReward = firstAgentReward;
        this.secondAgentReward = secondAgentReward;
    }

    public Agent firstAgent() {
        return firstAgent;
    }

    public Agent secondAgent() {
        return secondAgent;
    }

    public Bet firstAgentBet() {
        return firstAgentBet;
    }

    public Bet secondAgentBet() {
        return secondAgentBet;
    }

    public Reward firstAgentReward() {
        return firstAgentReward;
    }

    public Reward secondAgentReward() {
        return secondAgentReward;
    }

    public static class Builder {
        private Agent firstAgent;
        private Agent secondAgent;
        private Bet firstAgentBet;
        private Bet secondAgentBet;
        private Reward firstAgentReward;
        private Reward secondAgentReward;

        public Builder first(Agent agent, Bet bet, Reward reward) {
            this.firstAgent = agent;
            this.firstAgentBet = bet;
            this.firstAgentReward = reward;
            return this;
        }

        public Builder second(Agent agent, Bet bet, Reward reward) {
            this.secondAgent = agent;
            this.secondAgentBet = bet;
            this.secondAgentReward = reward;
            return this;
        }

        public PartResult build() {
            Objects.requireNonNull(firstAgent, "first agent must be not null");
            Objects.requireNonNull(secondAgent, "second agent must be not null");
            Objects.requireNonNull(firstAgentBet, "first agent bet must be not null");
            Objects.requireNonNull(secondAgentBet, "first agent bet must be not null");
            Objects.requireNonNull(firstAgentReward, "first agent reward must be not null");
            Objects.requireNonNull(secondAgentReward, "first agent reward must be not null");
            return new PartResult(firstAgent, secondAgent, firstAgentBet, secondAgentBet, firstAgentReward, secondAgentReward);
        }
    }
}
