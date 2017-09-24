package com.shurikat.cooperationgame.summary;

import com.shurikat.cooperationgame.core.Agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author Alex Ivchenko
 */
public final class GameSummary {
    private final Map<Agent, List<PartResult>> agentSummary;
    private final GameResult result;

    public static Builder builder() {
        return new Builder();
    }

    public GameResult result() {
        return result;
    }

    public Stream<PartResult> agentResults(Agent agent) {
        return agentSummary.get(agent).stream();
    }

    private GameSummary(Map<Agent, List<PartResult>> agentSummary) {
        GameResult.Builder resultBuilder = GameResult.builder();
        this.agentSummary = agentSummary;
        agentSummary.keySet()
                .forEach(resultBuilder::remaining);
        result = resultBuilder.build();
    }

    public static class Builder {
        private final Map<Agent, List<PartResult>> agentSummary = new HashMap<>();

        public Builder result(Agent agent, PartResult result) {
            agentSummary.putIfAbsent(agent, new ArrayList<>());
            agentSummary.get(agent).add(result);
            return this;
        }

        public GameSummary build() {
            return new GameSummary(agentSummary);
        }
    }
}
