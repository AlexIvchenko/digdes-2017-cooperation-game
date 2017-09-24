package com.shurikat.cooperationgame.summary;

import com.shurikat.cooperationgame.core.Agent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Alex Ivchenko
 */
public final class GameSummary {
    private final Map<Agent, PartResults> agentSummary;
    private final GameResult result;

    public static Builder builder() {
        return new Builder();
    }

    public GameResult result() {
        return result;
    }

    public PartResults agentResults(Agent agent) {
        return agentSummary.get(agent);
    }

    private GameSummary(List<PartResults> agentSummary) {
        GameResult.Builder resultBuilder = GameResult.builder();
        agentSummary
                .stream()
                .filter(PartResults::hasMoney)
                .forEach(partResults -> resultBuilder.remaining(partResults.agent()));
        result = resultBuilder.build();
        this.agentSummary = new HashMap<>();
        agentSummary.forEach(partResults -> this.agentSummary.put(partResults.agent(), partResults));
    }

    public static class Builder {
        private final Map<Agent, PartResults.Builder> agentSummary = new HashMap<>();

        public Builder result(Agent agent, PartResult result) {
            agentSummary.putIfAbsent(agent, PartResults.builder());
            agentSummary.get(agent).agent(agent).add(result);
            return this;
        }

        public GameSummary build() {
            List<PartResults> list = agentSummary.entrySet().stream()
                    .map(entry -> entry.getValue().build())
                    .collect(Collectors.toList());
            return new GameSummary(list);
        }
    }
}
