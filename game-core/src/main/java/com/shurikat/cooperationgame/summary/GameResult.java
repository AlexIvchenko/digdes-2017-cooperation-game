package com.shurikat.cooperationgame.summary;

import com.shurikat.cooperationgame.core.Agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Alex Ivchenko
 */
public final class GameResult {
    private final List<Agent> remaining;

    public static Builder builder() {
        return new Builder();
    }

    public Optional<Agent> winner() {
        if (isFinished()) {
            return Optional.of(remaining.get(0));
        } else {
            return Optional.empty();
        }
    }

    public Stream<Agent> remaining() {
        return remaining.stream();
    }

    public boolean isFinished() {
        return remaining.size() == 1;
    }

    private GameResult(List<Agent> remaining) {
        this.remaining = remaining;
    }

    public static class Builder {
        private final List<Agent> remaining = new ArrayList<>();

        public Builder remaining(Agent agent) {
            remaining.add(agent);
            return this;
        }

        public GameResult build() {
            return new GameResult(remaining);
        }
    }
}
