package com.shurikat.cooperationgame.core;

import com.shurikat.cooperationgame.summary.GameSummary;
import com.shurikat.cooperationgame.summary.PartResult;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public final class Game {
    private final Set<Agent> agents;
    private final GameSummary.Builder summaryBuilder = GameSummary.builder();

    private Game(Set<Agent> agents) {
        this.agents = new HashSet<>(agents);
    }

    public static Builder builder() {
        return new Builder();
    }

    public GameSummary execute(int maxRound) {
        for (int r = 0; r < maxRound && !isFinished(); ++r) {
            makeRound().execute().results().forEach(this::applyPartResult);
        }
        return summaryBuilder.build();
    }

    private Round makeRound() {
        Round.Builder roundBuilder = Round.builder();
        agents.stream()
                .filter(Agent::hasMoney)
                .forEach(roundBuilder::addAgent);
        return roundBuilder.build();
    }

    private void applyPartResult(PartResult result) {
        summaryBuilder.result(result.firstAgentSnapshot().agent, result);
        summaryBuilder.result(result.secondAgentSnapshot().agent, result);
    }

    private boolean isFinished() {
        return agents.stream()
                .filter(Agent::hasMoney)
                .count() <= 1;
    }

    public static class Builder {
        private final Set<Agent> agents = new HashSet<>();

        public Builder addAgent(Agent agent) {
            agents.add(agent);
            return this;
        }

        public Builder addAllAgents(Set<Agent> agents) {
            agents.addAll(agents);
            return this;
        }

        public Game build() {
            return new Game(agents);
        }
    }
}
