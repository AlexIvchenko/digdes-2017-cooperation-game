package com.shurikat.cooperationgame.project;

import com.shurikat.cooperationgame.agents.Agents;
import com.shurikat.cooperationgame.agents.ProbabilityBetStrategy;
import com.shurikat.cooperationgame.core.Agent;
import com.shurikat.cooperationgame.core.Game;
import com.shurikat.cooperationgame.summary.GameSummary;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Alex Ivchenko
 */
final class Tournament {
    private final Random random = new Random(System.currentTimeMillis());
    private final Settings settings;
    private final HashMap<Integer, Integer> probabilityToCountWins = new HashMap<>();

    Tournament(Settings settings) {
        this.settings = settings;
    }

    static class Settings {
        private final int experiments;
        private final int maxRound;
        private final int countBots;
        private final int startMoney;

        Settings(int experiments, int maxRound, int countBots, int startMoney) {
            this.experiments = experiments;
            this.maxRound = maxRound;
            this.countBots = countBots;
            this.startMoney = startMoney;
        }
    }

    Statistic run() {
        for (int i = 0; i < settings.experiments; ++i) {
            GameSummary summary = iterate();
            Optional<Agent> best = findBestAgent(summary);
            if (best.isPresent()) {
                Agent bot = best.get();
                ProbabilityBetStrategy strategy = (ProbabilityBetStrategy) bot.strategy();
                int probability = strategy.probability();
                probabilityToCountWins.merge(probability, 1, (c1, c2) -> c1 + c2);
            }
        }
        return statistic();
    }

    private GameSummary iterate() {
        Game.Builder builder = Game.builder();
        for (int i = 0; i < settings.countBots; ++i) {
            Agent bot = Agents
                    .probability(random.nextInt(101))
                    .name("bot #" + i)
                    .money(settings.startMoney);
            builder.addAgent(bot);
        }
        Game game = builder.build();
        return game.proceed(settings.maxRound);
    }

    private Optional<Agent> findBestAgent(GameSummary summary) {
        return summary.result().remaining()
                .sorted(Comparator.comparingInt(Agent::money).reversed())
                .findFirst();
    }

    private Statistic statistic() {
        double[] prob = new double[101];
        AtomicInteger experiments = new AtomicInteger(0);
        probabilityToCountWins.values().forEach(experiments::addAndGet);
        probabilityToCountWins.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue() - e1.getValue())
                .forEach(entry -> prob[entry.getKey()] = (double) entry.getValue() / experiments.get());
        return new Statistic(prob);
    }
}
