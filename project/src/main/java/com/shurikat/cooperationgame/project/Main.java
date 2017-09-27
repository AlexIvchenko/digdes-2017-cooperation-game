package com.shurikat.cooperationgame.project;

import com.shurikat.cooperationgame.agents.Agents;
import com.shurikat.cooperationgame.core.Agent;
import com.shurikat.cooperationgame.core.Game;

import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public class Main {
    private final static Random random = new Random(System.currentTimeMillis());
    public static void main(final String... args) {
        Tournament.Settings settings = new Tournament.Settings(1000, 100, 20, 10);
        Statistic statistic = new Tournament(settings).run();
        print(statistic);
    }


    public static void finiteCase() {
        Game.Builder builder = Game.builder();
        final int m = 10; // pessimistic agents
        final int n = random.nextInt(2 * m + 1); // naive agents
        final int M = 10;
        Set<Agent> pessimistic = new HashSet<>();
        for (int i = 0; i < m; ++i) {
            Agent agent = Agents.pessimistic().name("p #" + i).money(M);
            pessimistic.add(agent);
        }
        Set<Agent> naive = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            Agent agent = Agents.naive().name("n #" + i).money(M);
            naive.add(agent);
        }
        System.out.println(m + " " + n);
        builder.addAll(pessimistic).addAll(naive);
        for (int i = 1; M + i * (n - 2 * m - 1) >= 0; ++i) {
            builder.build().proceed(1);
            final int r = i;
            System.out.println(naive.stream().allMatch(agent -> agent.money() == M + r * (n - 2 * m - 1)));
            System.out.println(pessimistic.stream().allMatch(agent -> agent.money() == M + r * (3 * n - m + 1)));
        }
    }

    private static void print(Statistic statistic) {
        System.out.printf("%15s\t%15s", "bet probability", "win probability\n");
        for (int p = 0; p <= 100; ++p) {
            System.out.printf(Locale.getDefault(), "%15s\t%15f\n", p + "%", statistic.winProbability(p));
        }
    }
}
