package com.shurikat.cooperationgame.project;

import java.util.Locale;

/**
 * @author Alex Ivchenko
 */
public class Main {
    public static void main(final String... args) {
        Tournament.Settings settings = new Tournament.Settings(1000, 100, 20, 10);
        Statistic statistic = new Tournament(settings).run();
        print(statistic);
    }

    private static void print(Statistic statistic) {
        System.out.printf("%15s\t%15s", "bet probability", "win probability\n");
        for (int p = 0; p <= 100; ++p) {
            System.out.printf(Locale.getDefault(), "%15d\t%15f\n", p, statistic.winProbability(p));
        }
    }
}
