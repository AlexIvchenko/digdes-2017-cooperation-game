package com.shurikat.cooperationgame.bots;

import com.shurikat.cooperationgame.botapi.BotAgent;

/**
 * @author Alex Ivchenko
 */
public class Bots {
    public static BotAgent.NameStageBuilder random() {
        return BotAgent.builder()
                .strategy(new RandomBetStrategy());
    }

    public static BotAgent.NameStageBuilder naive() {
        return BotAgent.builder()
                .strategy(new NaiveBetStrategy());
    }

    public static BotAgent.NameStageBuilder pessimistic() {
        return BotAgent.builder()
            .strategy(new PessimisticBetStrategy());
    }

    public static BotAgent.NameStageBuilder probability(int probability) {
        return BotAgent.builder()
                .strategy(new ProbabilityBetStrategy(probability));
    }
}
