package com.shurikat.cooperationgame.agents;

import com.shurikat.cooperationgame.core.Agent;

/**
 * @author Alex Ivchenko
 */
public class Agents {
    public static Agent.NameStageBuilder random() {
        return Agent.builder()
                .strategy(new RandomBetStrategy());
    }

    public static Agent.NameStageBuilder naive() {
        return Agent.builder()
                .strategy(new NaiveBetStrategy());
    }

    public static Agent.NameStageBuilder greedy() {
        return Agent.builder()
            .strategy(new GreedyBetStrategy());
    }

    public static Agent.NameStageBuilder probability(int probability) {
        return Agent.builder()
                .strategy(new ProbabilityBetStrategy(probability));
    }
}
