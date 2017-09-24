package com.shurikat.cooperationgame.summary;

import com.shurikat.cooperationgame.core.Agent;
import com.shurikat.cooperationgame.core.Bet;
import com.shurikat.cooperationgame.core.Reward;

import java.util.Objects;

/**
 * @author Alex Ivchenko
 */
public class AgentSnapshot {
    public final Agent agent;
    public final int moneyBefore;
    public final Bet bet;
    public final Reward reward;
    public final int moneyAfter;

    public static AgentStageBuilder builder() {
        return new BuilderImpl();
    }

    private AgentSnapshot(Agent agent, int moneyBefore, Bet bet, Reward reward, int moneyAfter) {
        this.agent = agent;
        this.moneyBefore = moneyBefore;
        this.bet = bet;
        this.reward = reward;
        this.moneyAfter = moneyAfter;
    }

    private static class BuilderImpl implements AgentStageBuilder, MoneyBeforeStageBuilder, BetStageBuilder, RewardStageBuilder, MoneyAfterStageBuilder {
        private Agent agent;
        private int moneyBefore;
        private Bet bet;
        private Reward reward;
        private int moneyAfter;

        @Override
        public MoneyBeforeStageBuilder agent(Agent agent) {
            Objects.requireNonNull(agent);
            this.agent = agent;
            return this;
        }

        @Override
        public BetStageBuilder moneyBefore(int money) {
            this.moneyBefore = money;
            return this;
        }

        @Override
        public RewardStageBuilder bet(Bet bet) {
            Objects.requireNonNull(bet);
            this.bet = bet;
            return this;
        }

        @Override
        public MoneyAfterStageBuilder reward(Reward reward) {
            Objects.requireNonNull(reward);
            this.reward = reward;
            return this;
        }

        @Override
        public AgentSnapshot moneyAfter(int money) {
            this.moneyAfter = money;
            return new AgentSnapshot(agent, moneyBefore, bet, reward, moneyAfter);
        }
    }

    public interface AgentStageBuilder {
        MoneyBeforeStageBuilder agent(Agent agent);
    }

    public interface MoneyBeforeStageBuilder {
        BetStageBuilder moneyBefore(int money);
    }

    public interface BetStageBuilder {
        RewardStageBuilder bet(Bet bet);
    }

    public interface RewardStageBuilder {
        MoneyAfterStageBuilder reward(Reward reward);
    }

    public interface MoneyAfterStageBuilder {
        AgentSnapshot moneyAfter(int money);
    }
}
