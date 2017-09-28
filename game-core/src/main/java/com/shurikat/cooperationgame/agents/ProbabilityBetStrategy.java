package com.shurikat.cooperationgame.agents;

import com.shurikat.cooperationgame.core.BetStrategy;
import com.shurikat.cooperationgame.core.Bet;

import java.util.Random;

/**
 * @author Alex Ivchenko
 */
public final class ProbabilityBetStrategy implements BetStrategy {
    private final Random random = new Random(System.currentTimeMillis());
    private final int probability;

    public ProbabilityBetStrategy() {
        this(50);
    }

    public ProbabilityBetStrategy(int probability) {
        if (probability < 0 || probability > 100) {
            throw new IllegalArgumentException("probability must be in [0; 100]");
        }
        this.probability = probability;
    }

    @Override
    public Bet bet() {
        int r = random.nextInt(100); // 0..99
        if (probability > r) {
            return Bet.ONE;
        } else {
            return Bet.ZERO;
        }
    }

    public int probability() {
        return probability;
    }

    @Override
    public String name() {
        return "probability(" + probability + ")";
    }
}
