package com.shurikat.cooperationgame.summary;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Alex Ivchenko
 */
public final class RoundSummary {
    private final List<PartResult> results;

    public static Builder builder() {
        return new Builder();
    }

    public Stream<PartResult> results() {
        return results.stream();
    }

    private RoundSummary(List<PartResult> results) {
        this.results = results;
    }

    public static class Builder {
        private final List<PartResult> results = new ArrayList<>();

        public Builder result(PartResult result) {
            results.add(result);
            return this;
        }

        public RoundSummary build() {
            return new RoundSummary(results);
        }
    }
}
