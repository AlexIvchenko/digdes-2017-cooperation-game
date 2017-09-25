package com.shurikat.cooperationgame.core;

import com.shurikat.cooperationgame.summary.PartResult;
import com.shurikat.cooperationgame.summary.RoundSummary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
final class Round {
    private final ArrayList<Agent> agents;
    private boolean executed = false;

    static Builder builder() {
        return new Builder();
    }

    private Round(Set<Agent> agents) {
        this.agents = new ArrayList<>(agents);
    }

    RoundSummary execute() {
        if (executed) {
            throw new IllegalStateException("this round already executed");
        }
        RoundSummary.Builder summaryBuilder = RoundSummary.builder();
        for (int i = 0; i < agents.size(); ++i) {
            for (int j = i + 1; j < agents.size(); ++j) {
                summaryBuilder.result(makePair(i, j));
            }
        }
        executed = true;
        return summaryBuilder.build();
    }

    private PartResult makePair(int i, int j) {
        return new Part(agents.get(i), agents.get(j)).execute();
    }

    static class Builder {
        private final Set<Agent> agents = new HashSet<>();

        public Builder addAgent(Agent agent) {
            if (!agent.hasMoney()) {
                throw new IllegalArgumentException("agent must have money");
            }
            agents.add(agent);
            return this;
        }

        public Builder addAll(Set<Agent> agents) {
            agents.forEach(this::addAgent);
            return this;
        }

        public Round build() {
            if (agents.size() < 2) {
                throw new IllegalArgumentException("agents quantity in round must be al least 2");
            }
            return new Round(agents);
        }
    }
}
