package com.shurikat.cooperationgame.core;

import com.shurikat.cooperationgame.summary.PartResult;
import com.shurikat.cooperationgame.summary.RoundSummary;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex Ivchenko
 */
final class Round {
    private final ArrayList<AppliedAgent> agents;

    static Builder builder() {
        return new Builder();
    }

    private Round(List<AppliedAgent> agents) {
        this.agents = new ArrayList<>(agents);
    }

    RoundSummary execute() {
        RoundSummary.Builder summaryBuilder = RoundSummary.builder();
        for (int i = 0; i < agents.size(); ++i) {
            for (int j = i + 1; j < agents.size(); ++j) {
                Part part = new Part(agents.get(i), agents.get(j));
                PartResult partResult = part.execute();
                summaryBuilder.result(partResult);
            }
        }
        return summaryBuilder.build();
    }

    static class Builder {
        private final ArrayList<AppliedAgent> agents = new ArrayList<>();

        public Builder addAgent(AppliedAgent agent) {
            agents.add(agent);
            return this;
        }

        public Round build() {
            return new Round(agents);
        }
    }
}
