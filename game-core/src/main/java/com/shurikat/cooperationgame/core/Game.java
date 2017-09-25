package com.shurikat.cooperationgame.core;

import com.shurikat.cooperationgame.summary.GameSummary;
import com.shurikat.cooperationgame.summary.PartResult;

import java.util.HashSet;
import java.util.Objects;
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

    public GameSummary proceed(int maxRound) {
        for (int r = 0; r < maxRound && !isFinished(); ++r) {
            makeRound().execute().results().forEach(this::applyPartResult);
        }
        return summaryBuilder.build();
    }

    private Round makeRound() {
        Round.Builder roundBuilder = Round.builder();
        agents.stream()
                .filter(Agent::canPlay)
                .forEach(roundBuilder::addAgent);
        return roundBuilder.build();
    }

    private void applyPartResult(PartResult result) {
        summaryBuilder.result(result.firstAgentSnapshot().agent, result);
        summaryBuilder.result(result.secondAgentSnapshot().agent, result);
    }

    private boolean isFinished() {
        return agents.stream()
                .filter(Agent::canPlay)
                .count() <= 1;
    }

    public static class Builder {
        private final Set<Agent> agents = new HashSet<>();

        public Builder addAgent(Agent agent) {
            checkAgent(agent);
            agents.add(agent);
            return this;
        }

        public Builder addAll(Set<Agent> agents) {
            Objects.requireNonNull(agents, "set cannot be null");
            agents.forEach(this::checkAgent);
            this.agents.addAll(agents);
            return this;
        }

        private void checkAgent(Agent agent) {
            Objects.requireNonNull(agent, "agent must be not null");
            if (!agent.canPlay()) {
                throw new IllegalArgumentException("agent" + agent + "don't have money");
            }
        }

        public Game build() {
            if (agents.size() < 2) {
                throw new IllegalArgumentException("this game requires at least 2 agents");
            }
            return new Game(agents);
        }
    }
}
