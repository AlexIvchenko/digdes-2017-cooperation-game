package com.shurikat.cooperationgame.bots;

import com.shurikat.cooperationgame.botapi.BetStrategy;
import com.shurikat.cooperationgame.core.Bet;

import java.util.Random;

/**
 * @author Alex Ivchenko
 */
public class ProbabilityBetStrategy implements BetStrategy {
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
        int r = random.nextInt(10); // 0..99
        if (probability > r) {
            return Bet.ONE;
        } else {
            return Bet.ZERO;
        }
    }
}
