package com.shurikat.cooperationgame.summary;

import com.shurikat.cooperationgame.core.Agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Alex Ivchenko
 */
public final class GameSummary {
    private final Map<Agent, AgentResults> agentSummary;
    private final GameResult result;

    public static Builder builder() {
        return new Builder();
    }

    public GameResult result() {
        return result;
    }

    public AgentResults agentResults(Agent agent) {
        return agentSummary.get(agent);
    }

    private GameSummary(List<AgentResults> agentSummary) {
        GameResult.Builder resultBuilder = GameResult.builder();
        agentSummary
                .stream()
                .filter(res -> res.agent().hasMoney())
                .forEach(agentResults -> resultBuilder.remaining(agentResults.agent()));
        result = resultBuilder.build();
        this.agentSummary = new HashMap<>();
        agentSummary.forEach(agentResults -> this.agentSummary.put(agentResults.agent(), agentResults));
    }

    public static class Builder {
        private final Map<Agent, List<PartResult>> agentSummary = new HashMap<>();

        public Builder result(Agent agent, PartResult result) {
            agentSummary.putIfAbsent(agent, new ArrayList<>());
            agentSummary.get(agent).add(result);
            return this;
        }

        public GameSummary build() {
            List<AgentResults> results = agentSummary.entrySet().stream()
                    .map(entry -> {
                        Agent agent = entry.getKey();
                        List<PartResult> list = entry.getValue();
                        return AgentResults.builder().agent(agent).addAll(list).build();
                    }).collect(Collectors.toList());
            return new GameSummary(results);
        }
    }
}
