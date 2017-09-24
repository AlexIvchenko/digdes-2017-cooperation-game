package com.shurikat.cooperationgame.core;

import com.shurikat.cooperationgame.summary.PartResult;

/**
 * @author Alex Ivchenko
 */
final class Part {
    private final AppliedAgent firstAgent;
    private final AppliedAgent secondAgent;

    Part(AppliedAgent firstAgent, AppliedAgent secondAgent) {
        this.firstAgent = firstAgent;
        this.secondAgent = secondAgent;
    }

    PartResult execute() {
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

        return PartResult.builder()
                .first(firstAgent.agent(), fBet, fReward)
                .second(secondAgent.agent(), sBet, sReward)
                .build();
    }
}
