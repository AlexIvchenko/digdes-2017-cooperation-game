package com.shurikat.cooperationgame;

import com.shurikat.cooperationgame.core.Agent;
import com.shurikat.cooperationgame.core.Bet;

import java.util.Random;

/**
 * @author Alex Ivchenko
 */
public class Utils {
    private static final Random ran = new Random(0);
    public static Agent agent(String name, int money, Bet bet) {
        return new Agent(name, money) {
            @Override
            public Bet bet() {
                return bet;
            }
        };
    }

    public static Agent anyAgent() {
        return new Agent(Integer.toHexString(ran.nextInt()), ran.nextInt(100)) {
            @Override
            public Bet bet() {
                return Bet.values()[ran.nextInt(2)];
            }
        };
    }

    public static Agent nullAgent() {
        return null;
    }
}
