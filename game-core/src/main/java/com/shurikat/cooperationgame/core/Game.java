package com.shurikat.cooperationgame.core;

import com.shurikat.cooperationgame.summary.GameSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alex Ivchenko
 */
public final class Game {
    private final List<AppliedAgent> agents;

    public Game(List<Agent> agents) {
        this.agents = agents.stream()
                .map(agent -> agent.applyGame(this))
                .collect(Collectors.toList());
    }

    public static Builder builder() {
        return new Builder();
    }

    public GameSummary execute(int maxRound) {
        GameSummary.Builder summaryBuilder = GameSummary.builder();
        for (int r = 0; r < maxRound && !isFinished(); ++r) {
            makeRound().execute().results().forEach(partResult -> {
                summaryBuilder.result(partResult.firstAgentSnapshot().agent, partResult);
                summaryBuilder.result(partResult.secondAgentSnapshot().agent, partResult);
            });
        }
        return summaryBuilder.build();
    }

    private Round makeRound() {
        Round.Builder roundBuilder = Round.builder();
        agents.stream()
                .filter(AppliedAgent::hasMoney)
                .forEach(roundBuilder::addAgent);
        return roundBuilder.build();
    }

    private boolean isFinished() {
        return agents.stream()
                .filter(AppliedAgent::hasMoney)
                .count() == 1;
    }

    public static class Builder {
        private final List<Agent> agents = new ArrayList<>();

        public Builder agent(Agent agent) {
            agents.add(agent);
            return this;
        }

        public Game build() {
            return new Game(agents);
        }
    }
}
