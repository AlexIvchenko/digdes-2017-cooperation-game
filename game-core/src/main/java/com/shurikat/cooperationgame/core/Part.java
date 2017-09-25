package com.shurikat.cooperationgame.core;

import com.shurikat.cooperationgame.summary.AgentSnapshot;
import com.shurikat.cooperationgame.summary.PartResult;

import java.util.Objects;

/**
 * @author Alex Ivchenko
 */
final class Part {
    private final Agent firstAgent;
    private final Agent secondAgent;
    private boolean executed = false;

    Part(Agent firstAgent, Agent secondAgent) {
        this.firstAgent = firstAgent;
        this.secondAgent = secondAgent;
        checkAgents();
    }

    private void checkAgents() {
        Objects.requireNonNull(firstAgent);
        Objects.requireNonNull(secondAgent);
    }

    PartResult execute() {
        if (executed) {
            throw new IllegalStateException("this part already executed");
        }
        int fMoneyBefore = firstAgent.money();
        int sMoneyBefore = secondAgent.money();
        Bet fBet = firstAgent.bet();
        Bet sBet = secondAgent.bet();
        Reward fReward = null;
        Reward sReward = null;

        if (fBet == Bet.ONE && sBet == Bet.ONE) {
            fReward = Reward.PLUS_TWO;
            sReward = Reward.PLUS_TWO;
        } else if (fBet == Bet.ZERO && sBet == Bet.ZERO) {
            fReward = Reward.MINUS_ONE;
            sReward = Reward.MINUS_ONE;
        } else if (fBet == Bet.ONE && sBet == Bet.ZERO) {
            fReward = Reward.MINUS_ONE;
            sReward = Reward.PLUS_THREE;
        } else if (fBet == Bet.ZERO && sBet == Bet.ONE) {
            fReward = Reward.PLUS_THREE;
            sReward = Reward.MINUS_ONE;
        }

        firstAgent.reward(fReward);
        secondAgent.reward(sReward);

        int fMoneyAfter = firstAgent.money();
        int sMoneyAfter = secondAgent.money();

        AgentSnapshot fSnapshot = AgentSnapshot.builder()
                .agent(firstAgent)
                .moneyBefore(fMoneyBefore)
                .bet(fBet)
                .reward(fReward)
                .moneyAfter(fMoneyAfter);

        AgentSnapshot sSnapshot = AgentSnapshot.builder()
                .agent(secondAgent)
                .moneyBefore(sMoneyBefore)
                .bet(sBet)
                .reward(sReward)
                .moneyAfter(sMoneyAfter);

        executed = true;

        return PartResult.builder()
                .first(fSnapshot)
                .second(sSnapshot)
                .build();
    }
}
