package project;

import com.shurikat.cooperationgame.botapi.BotAgent;
import com.shurikat.cooperationgame.core.Agent;
import com.shurikat.cooperationgame.summary.GameSummary;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Alex Ivchenko
 */
public final class Statistic {
    private final List<String> stat;

    public static Builder builder() {
        return new Builder();
    }

    private Statistic(List<String> stat) {
        this.stat = stat;
    }

    public Stream<String> get() {
        return stat.stream();
    }

    public static class Builder {
        private final HashMap<String, Integer> nameToCountWins = new HashMap<>();
        private int experiments;
        private Tournament.Settings settings;

        public Builder experiments(int experiments) {
            this.experiments = experiments;
            return this;
        }

        public Builder settings(Tournament.Settings settings) {
            this.settings = settings;
            return this;
        }

        public Statistic build() {
            for (int i = 0; i < experiments; ++i) {
                Tournament tournament = new Tournament(settings);
                GameSummary summary = tournament.run();
                findBestAgent(summary).ifPresent(agent -> nameToCountWins.merge(((BotAgent) agent).strategy().name(), 1, (i1, i2) -> i1 + i2));
            }
            return new Statistic(stat());
        }

        private Optional<Agent> findBestAgent(GameSummary summary) {
            return summary.result().remaining()
                    .sorted(Comparator.comparingInt(Agent::money).reversed())
                    .findFirst();
        }

        private List<String> stat() {
            return nameToCountWins.entrySet().stream()
                    .sorted((e1, e2) -> e2.getValue() - e1.getValue())
                    .map(entry -> entry.getKey() + " - " + entry.getValue())
                    .collect(Collectors.toList());
        }
    }
}
