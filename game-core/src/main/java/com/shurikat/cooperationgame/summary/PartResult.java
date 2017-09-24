package com.shurikat.cooperationgame.summary;

import java.util.Objects;

/**
 * @author Alex Ivchenko
 */
public final class PartResult {
    private final AgentSnapshot firstAgent;
    private final AgentSnapshot secondAgent;

    public static Builder builder() {
        return new Builder();
    }

    public AgentSnapshot firstAgentSnapshot() {
        return firstAgent;
    }

    public AgentSnapshot secondAgentSnapshot() {
        return secondAgent;
    }

    private PartResult(AgentSnapshot firstAgent, AgentSnapshot secondAgent) {
        this.firstAgent = firstAgent;
        this.secondAgent = secondAgent;
    }

    public static class Builder {
        private AgentSnapshot firstSnapshot;
        private AgentSnapshot secondSnapshot;
       public Builder first(AgentSnapshot snapshot) {
            this.firstSnapshot = snapshot;
            return this;
        }

        public Builder second(AgentSnapshot snapshot) {
            this.secondSnapshot = snapshot;
            return this;
        }

        public PartResult build() {
            Objects.requireNonNull(firstSnapshot, "first agent snapshot must be not null");
            Objects.requireNonNull(secondSnapshot, "first agent snapshot must be not null");
            return new PartResult(firstSnapshot, secondSnapshot);
        }
    }
}
