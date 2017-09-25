package com.shurikat.cooperationgame.summary;

import com.shurikat.cooperationgame.core.Agent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author Alex Ivchenko
 */
public class AgentResults {
    private final Agent agent;
    private final List<PartResult> results;

    public static AgentStageBuilder builder() {
        return new Builder();
    }

    public Agent agent() {
        return agent;
    }

    public Stream<PartResult> stream() {
        return results.stream();
    }

    private AgentResults(Agent agent, List<PartResult> results) {
        this.agent = agent;
        this.results = results;
    }

    public interface AgentStageBuilder {
        PartResultStageBuilder agent(Agent agent);
    }

    public interface PartResultStageBuilder {
        PartResultStageBuilder add(PartResult result);
        PartResultStageBuilder addAll(Collection<PartResult> results);
        AgentResults build();
    }

    private static class Builder implements AgentStageBuilder, PartResultStageBuilder {
        private Agent agent;
        private final List<PartResult> results = new ArrayList<>();

        @Override
        public PartResultStageBuilder agent(Agent agent) {
            Objects.requireNonNull(agent);
            this.agent = agent;
            return this;
        }

        @Override
        public PartResultStageBuilder add(PartResult result) {
            Objects.requireNonNull(result);
            results.add(result);
            return this;
        }

        @Override
        public PartResultStageBuilder addAll(Collection<PartResult> results) {
            this.results.addAll(results);
            return this;
        }

        @Override
        public AgentResults build() {
            return new AgentResults(agent, results);
        }
    }
}
