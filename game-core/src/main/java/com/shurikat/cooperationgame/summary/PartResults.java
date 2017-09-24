package com.shurikat.cooperationgame.summary;

import com.shurikat.cooperationgame.core.Agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author Alex Ivchenko
 */
public class PartResults {
    private final Agent agent;
    private final List<PartResult> results;

    public static Builder builder() {
        return new Builder();
    }

    public Agent agent() {
        return agent;
    }

    public PartResult last() {
        return results.get(results.size() - 1);
    }

    public boolean hasMoney() {
        if (last().firstAgentSnapshot().agent.equals(agent)) {
            return last().firstAgentSnapshot().moneyAfter > 0;
        }
        if (last().secondAgentSnapshot().agent.equals(agent)) {
            return last().secondAgentSnapshot().moneyAfter > 0;
        }
        throw new IllegalStateException();
    }

    public Stream<PartResult> stream() {
        return results.stream();
    }

    private PartResults(Agent agent, List<PartResult> results) {
        this.agent = agent;
        this.results = results;
    }

    public static class Builder {
        private Agent agent;
        private final List<PartResult> results = new ArrayList<>();

        public Builder agent(Agent agent) {
            this.agent = agent;
            return this;
        }

        public Builder add(PartResult result) {
            results.add(result);
            return this;
        }

        public PartResults build() {
            Objects.requireNonNull(agent);
            return new PartResults(agent, results);
        }
    }
}
