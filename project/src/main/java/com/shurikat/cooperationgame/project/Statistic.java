package com.shurikat.cooperationgame.project;

import java.util.Arrays;

/**
 * @author Alex Ivchenko
 */
final class Statistic {
    private final double[] probability;

    Statistic(double[] probability) {
        this.probability = Arrays.copyOf(probability, 101);
    }

    double winProbability(int betProbability) {
        return probability[betProbability];
    }
}
